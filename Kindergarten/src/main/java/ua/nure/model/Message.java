package ua.nure.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String id;
    @Column
    private String content;
    @ManyToOne(optional = false)
    @JoinColumn(name = "fro")
    private Human from;
    @ManyToOne(optional = false)
    @JoinColumn(name = "too")
    private Human to;

    public Message() {
    }

    public Message(String content, Human from, Human to) {
        this.content = content;
        this.from = from;
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Human getFrom() {
        return from;
    }

    public void setFrom(Human from) {
        this.from = from;
    }

    public Human getTo() {
        return to;
    }

    public void setTo(Human to) {
        this.to = to;
    }
}
