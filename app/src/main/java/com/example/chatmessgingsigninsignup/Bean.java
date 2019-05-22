package com.example.chatmessgingsigninsignup;

public class Bean {
    String messagae;
    String username;

    public Bean() {
    }

    public Bean(String messagae, String username) {
        this.messagae = messagae;
        this.username = username;
    }

    public String getMessagae() {
        return messagae;
    }

    public void setMessagae(String messagae) {
        this.messagae = messagae;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
