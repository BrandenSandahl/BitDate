package com.sixtel.bitdate;

import com.parse.ParseUser;

/**
 * Created by branden on 8/23/16.
 */
public class UserDAO {
    private static User sCurrentUser;

    private static final String COLUMN_FIRST_NAME= "firstName";
    private static final String COLUMN_PICTURE_URL = "pictureURL";



    public static User getCurrentUser() {
        if (sCurrentUser == null && ParseUser.getCurrentUser() != null) {
            ParseUser user = ParseUser.getCurrentUser();
            sCurrentUser = new User();
            sCurrentUser.setFirstName(user.getString(COLUMN_FIRST_NAME));
            sCurrentUser.setPictureURL(user.getString(COLUMN_PICTURE_URL));
        }
        return sCurrentUser;
    }



}
