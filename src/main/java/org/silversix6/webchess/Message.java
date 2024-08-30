package org.silversix6.webchess;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Message {

    int messageId;
    List<String> messages;
    MessageType messageType;

    public Message(int messageId, List<String> messages, MessageType messageType) {
        this.messageId = messageId;
        this.messages = messages;
        this.messageType = messageType;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}

