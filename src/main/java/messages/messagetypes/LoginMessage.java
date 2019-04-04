package messages.messagetypes;

import com.google.gson.Gson;
import data.RawLogin;
import messages.MessageType;

import java.nio.charset.StandardCharsets;

public class LoginMessage extends MessageClass {
    /**
     * Client->Server
     *
     * Client tries to log in.
     * Server should check if the username and password are correct and send back a SetSessionMessage that contains
     * a new Session object so next time when the user connects, the client can send a SetSessionMessage to the server
     * first with the same Session object and continue without having to send the username and password.
     */
    public LoginMessage(RawLogin data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.LOGIN;
    }

    //serialization test
    /*public static void main(String[] args) {
        LoginMessage lm = new LoginMessage(new RawLogin("test", "pass"));
        System.out.println(new String(lm.toJsonBytes(), StandardCharsets.UTF_8));
        MessageClass mc = new Gson().fromJson(new String(lm.toJsonBytes(), StandardCharsets.UTF_8), LoginMessage.class);
        System.out.println(mc.getData());
    }*/
}
