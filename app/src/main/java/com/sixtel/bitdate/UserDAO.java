package com.sixtel.bitdate;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by branden on 8/23/16.
 */
public class UserDAO {
    private static User sCurrentUser;

    private static final String COLUMN_FIRST_NAME= "firstName";
    private static final String COLUMN_PICTURE_URL = "pictureURL";
    private static List<User> sUsers;


    public static User getCurrentUser() {
        if (sCurrentUser == null && ParseUser.getCurrentUser() != null) {
            sCurrentUser =  parseUserToUser(ParseUser.getCurrentUser());
        }
        return sCurrentUser;
    }

    public static void getAllUsers(final UserDataCallbacks callback) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("objectId", getCurrentUser().getId());
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null) {
                    List<User> users = new ArrayList<>();
                    for (ParseUser parseUser : list) {
                        User user = parseUserToUser(parseUser);
                        users.add(user);
                    }
                    if (callback != null) {
                        callback.onUsersFetched(users);
                    }
                } else {
                    Log.d("USERDAO", e.getMessage());
                }
            }
        });
    }


    private static User parseUserToUser(ParseUser parseUser) {
        User user = new User();
        user.setFirstName(parseUser.getString(COLUMN_FIRST_NAME));
        user.setPictureURL(parseUser.getString(COLUMN_PICTURE_URL));
        user.setId(parseUser.getObjectId());

        return user;
    }

    public interface UserDataCallbacks {
        void onUsersFetched(List<User> users);

    }


}
