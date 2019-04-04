package messages.messagetypes;

import messages.MessageType;

public class GetProjectListMessage extends MessageClass {

    /**
     * Client->Server
     *
     * Server should send back a SetProjectListMessage that contains the names and ids of the projects the user has access to.
     * Client requests a project list.
     */
    public GetProjectListMessage() {
        super(null);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GETPROJECTLIST;
    }
}
