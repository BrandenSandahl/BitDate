package com.sixtel.bitdate;

/**
 * Created by branden on 8/23/16.
 */
public class User {

    private String mFirstName;
    private String mPictureURL;
    private String mId;

    public User() {
    }

    public User(String firstName) {
        mFirstName = firstName;
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
}
