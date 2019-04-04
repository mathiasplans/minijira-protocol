package messages.messagetypes;

import data.RawProjectNameList;
import messages.MessageType;

public class SetProjectListMessage extends MessageClass {

    /**
     * Server->Client
     *
     * Server sends the project list to client.
     * @param data the RawProjectNameList containing the project names and project ids.
     */
    public SetProjectListMessage(RawProjectNameList data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SETPROJECTLIST;
    }
}
