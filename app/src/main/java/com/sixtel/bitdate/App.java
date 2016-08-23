package com.sixtel.bitdate;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

/**
 * Created by branden on 8/23/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("App Class", "App Started");
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "yTygxd7jhXpNtTPWy2poMjJR8bBtrG34IyUQxozg", "t9XBrODeYwkoH1OA45tWByK52mgnsOLUfkdmGMcm");
    }
}
