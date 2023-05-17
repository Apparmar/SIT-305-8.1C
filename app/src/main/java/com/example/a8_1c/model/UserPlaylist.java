package com.example.a8_1c.model;

public class UserPlaylist {
    private int playlist_item_id;
    private int user_id;
    private String urlStr;

    public UserPlaylist(int playlist_item_id, int user_id) {
        this.playlist_item_id = playlist_item_id;
        this.user_id = user_id;
    }

    public UserPlaylist() {}

    public int getPlaylist_id() {
        return playlist_item_id;
    }

    public void setPlaylist_id(int playlist_item_id) {
        this.playlist_item_id = playlist_item_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUrl() {
        return urlStr;
    }

    public void setUrl(String urlStr) {
        this.urlStr = urlStr;
    }
}
