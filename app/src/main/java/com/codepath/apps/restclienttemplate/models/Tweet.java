package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.LinkedList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public long tweet_id;
    public String created_at;
    public User user;

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.created_at = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.tweet_id = jsonObject.getLong("id");
        return tweet;
    }

    public Tweet(){
    }

    public static List<Tweet> listFromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new LinkedList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            tweets.add(fromJson(obj));
        }
        return tweets;
    }

    public String getFormattedTimestamp(){
        return TimeFormatter.getTimeDifference(this.created_at);
    }
}
