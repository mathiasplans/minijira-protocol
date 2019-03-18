import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class Message {
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private MessageType lastMessage;

    public Message(DataOutputStream outputStream, DataInputStream inputStream){
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    private String handleMessage(MessageType type, byte[] data) throws IOException {
        // TODO: the packet should also have an ID, so the server/client knows which message was responsed to
        // TODO: if needed, create an enum for error types. Just sending error is cryptic
        lastMessage = type;
        switch (type){
            case ECHO:
                // Echo the message back
                sendMessage(data, MessageType.RESPONSE);
                break;
            case ERROR:
                System.out.println(new String(data));
                data = new byte[0];
                break;
            case CREATETASK:
                if(!createTask(data))
                    sendMessage(data, MessageType.ERROR);
                break;
            case REMOVETASK:
                if(!removeTask(data))
                    sendMessage(data, MessageType.ERROR);
                break;
            case UPDATETIMETASK:
                if(!updateTimeTask(data))
                    sendMessage(data, MessageType.ERROR);
                break;
            case SETSTATUSTASK:
                if(!setStatusTask(data))
                    sendMessage(data, MessageType.ERROR);
                break;
            default:
                /* Handle error here, right now do nothing */
        }

        return new String(data);
    }

    public void sendMessage(byte[] message, MessageType type) throws IOException {
        // Send the type of the message
        outputStream.writeInt(type.getAsInt());

        // Send the length of the message
        outputStream.writeInt(message.length);

        // Send the data of the message
        outputStream.write(message);
    }

    public String readMessage() throws IOException {
        // Get the type of message
        MessageType messageType = MessageType.getMessageType(inputStream.readInt());

        // Get the length of the message
        int messageLen = inputStream.readInt();

        // Get the data
        byte[] data = new byte[messageLen];
        inputStream.readFully(data);

        return handleMessage(messageType, data);
    }

    public MessageType getMessageType(){
        return lastMessage;
    }

    /**
     *
     * @param data
     * @return
     */
    public abstract boolean createTask(byte[] data);

    /**
     *
     * @param data
     * @return
     */
    public abstract boolean removeTask(byte[] data);

    /**
     *
     * @param data
     * @return
     */
    public abstract boolean updateTimeTask(byte[] data);

    /**
     *
     * @param data
     * @return
     */
    public abstract boolean setStatusTask(byte[] data);
}
