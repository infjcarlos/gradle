package com.soft.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jcarlos on 7/25/2017.
 */
@Entity
@Table(name = "destination_message")
public class DestinationMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "destination_message_id")
    private Long id;

    @Column(name = "message_id")
    private Message message;

    @Column(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
