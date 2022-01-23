package com.openclassrooms.firebaseoc.ui.model;

import androidx.annotation.Nullable;

//L'utilisateur dans la bse de donnée
public class User {

    private String uid;
    private String username;
    private Boolean Notif;
    @Nullable
    private String urlPicture;

    public User() { }

    public User(String uid, String username, @Nullable String urlPicture) {
        this.uid = uid;
        this.username = username;
        this.urlPicture = urlPicture;
        this.Notif = false;
    }

    // --- GETTERS ---
    public String getUid() { return uid; }
    public String getUsername() { return username; }
    @Nullable
    public String getUrlPicture() { return urlPicture; }
    public Boolean getNotif() { return Notif; }

    // --- SETTERS ---
    public void setUsername(String username) { this.username = username; }
    public void setUid(String uid) { this.uid = uid; }
    public void setUrlPicture(@Nullable String urlPicture) { this.urlPicture = urlPicture; }
    public void setNotif(Boolean notif) { Notif = notif; }
}
