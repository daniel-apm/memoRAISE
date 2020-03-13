package dev.daniel.memoraise.Manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String KEY_MANAGER = "Memoraise";
    public static final String KEY_MEMO = "memo_list";

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    public SessionManager(Context context) {
        sp = context.getSharedPreferences(KEY_MANAGER, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void putSessionValue(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.apply();
    }

    public String getSessionValue(String keySp){
        return sp.getString(keySp,null);
    }
}
