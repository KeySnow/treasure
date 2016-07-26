package com.example.treasure.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用户仓库，使用前必须先对其进行初始化init()
 * Created by 93432 on 2016/7/14.
 */
public class UserPrefs {
    private final SharedPreferences preferences;
    private static UserPrefs userPrefs;

    private static final String PREFS_MAME = "user_info";
    private static final String KEY_TOKENID = "key_tokenid";
    private static final String KEY_PHOTO = "key_photo";

    public static void init(Context context){
        userPrefs = new UserPrefs(context);
    }

    private UserPrefs(Context context){
        preferences = context.getApplicationContext().getSharedPreferences(PREFS_MAME, Context.MODE_PRIVATE);
    }

    public static UserPrefs getInstance(){
        return userPrefs;
    }

    public void setTokenid(int tokenid){
        preferences.edit().putInt(KEY_TOKENID, tokenid).apply();
    }

    public void setPhoto(String photoUrl){
        preferences.edit().putString(KEY_PHOTO, photoUrl).apply();
    }

    public int getTokenid(){
        return preferences.getInt(KEY_TOKENID, -1);
    }

    public String getPhoto(){
        return preferences.getString(KEY_PHOTO, null);
    }


}
