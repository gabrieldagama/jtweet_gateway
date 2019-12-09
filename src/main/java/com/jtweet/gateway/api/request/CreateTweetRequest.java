package com.jtweet.gateway.api.request;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateTweetRequest {
    private HashMap<String,String> tweetUser;
    private String tweet;
    private ArrayList<String> hashTags;

    public HashMap<String, String> getTweetUser() {
        return tweetUser;
    }

    public void setTweetUser(HashMap<String, String> tweetUser) {
        this.tweetUser = tweetUser;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public ArrayList<String> getHashTags() {
        return hashTags;
    }

    public void setHashTags(ArrayList<String> hashTags) {
        this.hashTags = hashTags;
    }

    public void addTweetUserIdAndUsername(String id, String username) {
        HashMap<String,String> tweetUser = new HashMap<>();
        tweetUser.put("id", id);
        tweetUser.put("username", username);
        this.tweetUser = tweetUser;
    }
}
