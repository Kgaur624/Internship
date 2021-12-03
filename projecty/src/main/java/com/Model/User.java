package com.Model;

public class User {

    String twitterHandle;
    String name;
    String profileImageUrl;
    /**
     * @param twitterHandle   specifies ScreenName of user who posted tweet.
     * @param name            specifies name of user who posted tweet.
     * @param profileImageUrl specifies URL profileImage of user who posted tweet.
     */
    public User(String twitterHandle, String name, String profileImageUrl) {
        this.twitterHandle = twitterHandle;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

}