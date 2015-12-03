package com.app.pebkar;

import android.content.Context;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by David Elykx on 02-12-15.
 */
public class UserConnected {
    protected String username;
    protected String password;

    public UserConnected(String username, String password, final Context context) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Toast.makeText(context,context.getString(R.string.connected),Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,context.getString(R.string.connectionFailed),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
