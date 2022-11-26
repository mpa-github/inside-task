package com.mpa.insidetask.domain.models;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table(name = "Messages")
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "text", columnDefinition = "TEXT", nullable = false)
    private String text;

    @ManyToOne
    private User user;

    public Message() {
    }

    public Message(Long id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id) && text.equals(message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
