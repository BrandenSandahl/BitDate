package com.sixtel.bitdate;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> permissions = new ArrayList<String>();
                permissions.add("user_birthday");
                ParseFacebookUtils.logInWithReadPermissionsInBackground(SignInActivity.this, permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        //something bad happened
                        if (parseUser == null) {
                            Log.d(TAG, "error creating user");
                        } else if (parseUser.isNew()) {  //created a new user
                            Log.d(TAG, "created new user");
                            getFacebookInfo();

                        } else { //user already exists and is logged in
                            Log.d(TAG, "logged in existing user");
                            finish();
                        }
                    }
                });
            }
        });
    }


    //asks for extra FB info
    private void getFacebookInfo() {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "picture, first_name");
        new GraphRequest(AccessToken.getCurrentAccessToken(),
                "/me",
                parameters,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        JSONObject user = response.getJSONObject();
                        ParseUser currentUser = ParseUser.getCurrentUser();
                        currentUser.put("firstName", user.optString("first_name")); // put in the first name
                        currentUser.put("pictureURL", user.optJSONObject("picture") //put in the user picture
                                .optJSONObject("data")
                                .optString("url"));
                        currentUser.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    finish();
                                }
                            }
                        });
                    }
                }).executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
}
