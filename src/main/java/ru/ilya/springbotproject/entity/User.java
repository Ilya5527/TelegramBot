package ru.ilya.springbotproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")

public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                '}';
    }
}
