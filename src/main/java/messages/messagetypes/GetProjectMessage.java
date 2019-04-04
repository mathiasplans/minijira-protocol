package messages.messagetypes;

import messages.MessageType;

public class GetProjectMessage extends MessageClass {

    /**
     * Client->Server
     *
     * Server should send back a SetProjectMessage that contains the RawProject object describing the project.
     * Client requests a project/board.
     */
    public GetProjectMessage(Long projectId) {
        super(projectId);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GETPROJECT;
    }
}
