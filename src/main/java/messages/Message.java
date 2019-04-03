package messages;

import messages.messagetypes.*;

import java.io.*;

public class Message {
    private Session session;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private MessageType lastMessage;
    private JiraMessageHandler msgHandler;

    public Message(Session session, DataOutputStream outputStream, DataInputStream inputStream, JiraMessageHandler handler) {
        this.session = session;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.msgHandler = handler;

        if (session != null) {
            try {
                sendMessage(new SetSessionMessage(session));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private MessageClass handleMessage(MessageType type, byte[] data) throws IOException {
        // TODO: the packet should also have an ID, so the server/client knows which message was responsed to
        // TODO: if needed, create an enum for error types. Just sending error is cryptic
        lastMessage = type;
        MessageClass messageClass = MessageClass.parseMessageClass(data, type);
        ErrorMessage errorMessage = null;
        switch (type){
            case ECHO:
                // Echo the message back
                if (messageClass != null)
                    sendMessage(new ResponseMessage((byte[]) messageClass.getData()));
                break;
            case ERROR:
                if (messageClass != null)
                    System.out.println(messageClass.getData());
                else
                    System.out.println(new String(data));
                //data = new byte[0];
                break;
            case CREATETASK:
                errorMessage = msgHandler.createTask((CreateTaskMessage) messageClass);
                break;
            case REMOVETASK:
                errorMessage = msgHandler.removeTask((RemoveTaskMessage) messageClass);
                break;
            case UPDATETASK:
                errorMessage = msgHandler.updateTask((UpdateTaskMessage) messageClass);
                break;
            case LOGIN:
                errorMessage = msgHandler.login((LoginMessage) messageClass);
                break;
            case SETSESSION:
                errorMessage = msgHandler.setSession((SetSessionMessage) messageClass);
                break;
            case GETPROJECTLIST:
                errorMessage = msgHandler.getProjectList((GetProjectListMessage) messageClass);
                break;
            case SETPROJECTLIST:
                errorMessage = msgHandler.setProjectList((SetProjectListMessage) messageClass);
                break;
            case GETPROJECT:
                errorMessage = msgHandler.getProject((GetProjectMessage) messageClass);
                break;
            case SETPROJECT:
                errorMessage = msgHandler.setProject((SetProjectMessage) messageClass);
                break;
            case GETSERVERTASKLIST:
                errorMessage = msgHandler.getServerTaskList((GetServerTaskListMessage) messageClass);
                break;
            default:
                System.out.println("Received unknown message type.");
        }
        if (errorMessage != null)
            sendMessage(errorMessage);
        return messageClass;
    }

    private void sendMessage(byte[] message, MessageType type) throws IOException {
        // Send the type of the message
        outputStream.writeInt(type.getAsInt());

        // Send the length of the message
        outputStream.writeInt(message.length);

        // Send the data of the message
        outputStream.write(message);
    }

    public void sendMessage(MessageClass messageClass) throws IOException {
        sendMessage(messageClass.toJsonBytes(), messageClass.getMessageType());
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
