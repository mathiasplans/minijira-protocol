package messages;

import data.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ProtocolConnection {
    private Session session;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private MessageType lastMessage;
    private JiraMessageHandler msgHandler;

    /**
     *
     * @param session
     * @param outputStream
     * @param inputStream
     * @param handler
     */
    public ProtocolConnection(Session session, DataOutputStream outputStream, DataInputStream inputStream, JiraMessageHandler handler) {
        this.session = session;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.msgHandler = handler;

        if (session != null) {
            try {
                sendMessage(session, MessageType.SETSESSION);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Object handleMessage(MessageType type, byte[] data) throws IOException {
        // TODO: the packet should also have an ID, so the server/client knows which message was responsed to
        // TODO: if needed, create an enum for error types. Just sending error is cryptic
        lastMessage = type;
        Object messageClass = type.fromJson(data);
        RawError errorMessage = null;
        switch (type){
            case ECHO:
                // Echo the message back
                if (messageClass != null)
                    sendMessage(data, MessageType.RESPONSE);
                break;
            case ERROR:
                if (messageClass != null)
                    System.out.println("Received error: " + ((RawError)messageClass).error);
                else
                    System.out.println(new String(data));
                //data = new byte[0];
                break;
            case CREATETASK:
                errorMessage = msgHandler.createTask((RawTask) messageClass);
                break;
            case REMOVETASK:
                errorMessage = msgHandler.removeTask((Long) messageClass);
                break;
            case UPDATETASK:
                errorMessage = msgHandler.updateTask((RawTask) messageClass);
                break;
            case LOGIN:
                errorMessage = msgHandler.login((RawLogin) messageClass);
                break;
            case SETSESSION:
                errorMessage = msgHandler.setSession((RawSession) messageClass);
                break;
            case GETPROJECTLIST:
                errorMessage = msgHandler.getProjectList();
                break;
            case SETPROJECTLIST:
                errorMessage = msgHandler.setProjectList((RawProjectNameList) messageClass);
                break;
            case GETPROJECT:
                errorMessage = msgHandler.getProject((Long) messageClass);
                break;
            case SETPROJECT:
                errorMessage = msgHandler.setProject((RawProject) messageClass);
                break;
            case GETSERVERTASKLIST:
                errorMessage = msgHandler.getServerTaskList(messageClass);
                break;
            default:
                System.out.println("Received unknown message type.");
        }
        if (errorMessage != null)
            sendMessage(errorMessage, MessageType.ERROR);
        return messageClass;
    }

    private void sendMessage(byte[] message, MessageType type) throws IOException {
        // Send the type of the message
        outputStream.writeInt(type.getAsInt());

        // Send the length of the message
        outputStream.writeInt(message.length);

        String s = new String(message, StandardCharsets.UTF_8);

        // Send the data of the message
        outputStream.write(message);
    }
    public void sendMessage(Object rawObject, MessageType messageType) throws IOException {
        if (messageType.getTypeclass() != null && rawObject != null && rawObject.getClass() != messageType.getTypeclass())
            throw new RuntimeException("This message type requires rawObject of type " + messageType.getTypeclass().toString());
        sendMessage(messageType.toJsonBytes(rawObject), messageType);
    }

    public MessageType readMessage() throws IOException {
        // Get the type of message
        MessageType messageType = MessageType.getMessageType(inputStream.readInt());

        // Get the length of the message
        int messageLen = inputStream.readInt();

        // Get the data
        byte[] data = new byte[messageLen];
        inputStream.readFully(data);
        handleMessage(messageType, data);
        return messageType;
    }

    public MessageType getMessageType(){
        return lastMessage;
    }

}
