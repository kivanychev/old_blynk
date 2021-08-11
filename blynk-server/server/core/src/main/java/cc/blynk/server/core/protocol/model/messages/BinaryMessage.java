package cc.blynk.server.core.protocol.model.messages;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 2/1/2015.
 */
public class BinaryMessage extends MessageBase {

    private final byte[] data;

    public BinaryMessage(int messageId, short command, byte[] data) {
        super(messageId, command);
        this.data = data;
    }

    @Override
    public byte[] getBytes() {
        return data;
    }
}
