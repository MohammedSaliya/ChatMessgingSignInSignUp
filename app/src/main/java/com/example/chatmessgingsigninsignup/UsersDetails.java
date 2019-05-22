package com.example.chatmessgingsigninsignup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UsersDetails {

    public static String username;
    public static String password;
    public static String chatWith;

    public UsersDetails() {
    }

    public UsersDetails(String username, String password, String chatWith) {
        this.username = username;
        this.password = password;
        this.chatWith = chatWith;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }
}
