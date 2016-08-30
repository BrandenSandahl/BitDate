package com.sixtel.bitdate;

import java.io.Serializable;

/**
 * Created by branden on 8/23/16.
 */
public class User implements Serializable{

    private String mFirstName;
    private String mPictureURL;
    private String mId;
    private String mFacebookId;


    public User() {
    }

    public User(String firstName) {
        mFirstName = firstName;
    }

    public String getLargePictureURL() {
        return "https://graph.facebook.com/v2.3/" + mFacebookId + "/picture?type=large";
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getPictureURL() {
        return mPictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.mPictureURL = pictureURL;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getFacebookId() {
        return mFacebookId;
    }

    public void setFacebookId(String facebookId) {
        mFacebookId = facebookId;
    }
}
