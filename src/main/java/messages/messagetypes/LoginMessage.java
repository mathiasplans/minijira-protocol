package messages.messagetypes;

import com.google.gson.Gson;
import data.RawLogin;
import messages.MessageType;

import java.nio.charset.StandardCharsets;

public class LoginMessage extends MessageClass {
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
