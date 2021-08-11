package cc.blynk.server.notifications.push.ios;

import cc.blynk.server.notifications.push.GCMMessage;
import cc.blynk.server.notifications.push.enums.Priority;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 16.07.15.
 */
public class IOSGCMMessage implements GCMMessage {

    private static final ObjectWriter WRITER = new ObjectMapper()
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .writerFor(IOSGCMMessage.class);
    private final String to;
    private final Priority priority;
    private final IOSBody notification;

    public IOSGCMMessage(String to, Priority priority, String message, int dashId) {
        this.to = to;
        this.priority = priority;
        this.notification = new IOSBody(message, dashId);
    }

    @Override
    public String getToken() {
        return to;
    }

    @Override
    public void setTitle(String title) {
        this.notification.setTitle(title);
    }

    @Override
    public String toJson() throws JsonProcessingException {
        return WRITER.writeValueAsString(this);
    }
}
