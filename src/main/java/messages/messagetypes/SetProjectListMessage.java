package messages.messagetypes;

import data.RawProjectNameList;
import messages.MessageType;

public class SetProjectListMessage extends MessageClass {
    public SetProjectListMessage(RawProjectNameList data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SETPROJECTLIST;
    }
}
