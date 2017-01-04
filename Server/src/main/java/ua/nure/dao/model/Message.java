package ua.nure.dao.model;

public class Message {
    private long message_id;
    private String content;
    private long from;
    private long to;

    public Message() {
    }

    public Message(long message_id, String content, long from, long to) {
        this.message_id = message_id;
        this.content = content;
        this.from = from;
        this.to = to;
    }

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message ");
        sb.append("message_id=").append(message_id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", from=").append(from);
        sb.append(", to=").append(to);
        sb.append('\n');
        return sb.toString();
    }
}
