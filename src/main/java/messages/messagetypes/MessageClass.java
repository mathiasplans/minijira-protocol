package messages.messagetypes;

import com.google.gson.Gson;
import data.RawLogin;
import data.RawProject;
import data.RawProjectNameList;
import data.RawTask;
import messages.MessageType;
import messages.Session;

import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class MessageClass {
    private Object data;
    private static Gson gson = new Gson();

    public Object getData() {
        return data;
    }

    public MessageClass(Object data) {
        this.data = data;
    }

    public abstract MessageType getMessageType();

    public byte[] toJsonBytes() {
        return gson.toJson(data).getBytes(StandardCharsets.UTF_8);
    }

    public static MessageClass parseMessageClass(byte[] data, MessageType type) {
        switch (type) {
            case GETPROJECT:
                return new GetProjectMessage(gson.fromJson(new String(data, StandardCharsets.UTF_8), Long.class));
            case SETPROJECT:
                return new SetProjectMessage(gson.fromJson(new String(data, StandardCharsets.UTF_8), RawProject.class));
            case GETPROJECTLIST:
                return new GetProjectListMessage();
            case SETPROJECTLIST:
                return new SetProjectListMessage(gson.fromJson(new String(data, StandardCharsets.UTF_8), RawProjectNameList.class));
            case SETSESSION:
                return new SetSessionMessage(gson.fromJson(new String(data, StandardCharsets.UTF_8), Session.class));
            case LOGIN:
                return new LoginMessage(gson.fromJson(new String(data, StandardCharsets.UTF_8), RawLogin.class));
            case GETSERVERTASKLIST:
                return new GetServerTaskListMessage(gson.fromJson(new String(data, StandardCharsets.UTF_8), Long.class));
            case UPDATETASK:
                return new UpdateTaskMessage(gson.fromJson(new String(data, StandardCharsets.UTF_8), RawTask.class));
            case REMOVETASK:
                return new RemoveTaskMessage(gson.fromJson(new String(data, StandardCharsets.UTF_8), Long.class));
            case CREATETASK:
                return new CreateTaskMessage(gson.fromJson(new String(data, StandardCharsets.UTF_8), RawTask.class));
            case ECHO:
                return new EchoMessage(data);
            case RESPONSE:
                return new ResponseMessage(data);
            case ERROR:
                return new ErrorMessage(new String(data, StandardCharsets.UTF_8));
            default:
                return null;
        }

    }
}
