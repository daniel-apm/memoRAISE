package dev.daniel.memoraise.Presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import dev.daniel.memoraise.Interface.IMemo;
import dev.daniel.memoraise.Manager.SessionManager;
import dev.daniel.memoraise.Model.MemoModel;

public class MemoPresenter {

    private IMemo presenterCallback;
    private ArrayList<MemoModel> memoList;
    private Gson gson;
    private String data;
    private SessionManager session;

    public MemoPresenter(Context context, IMemo presenterCallback) {
        this.presenterCallback = presenterCallback;
        gson = new Gson();
        session = new SessionManager(context);
    }

    public void initMemoData() {
        data = session.getSessionValue(SessionManager.KEY_MEMO);
        Type type = new TypeToken<ArrayList<MemoModel>>() {}.getType();
        memoList = gson.fromJson(data, type);
        if (memoList == null) {
            memoList = new ArrayList<>();
        }
        presenterCallback.initAdapter(memoList);
    }

    public void modifyMemoData(ArrayList<MemoModel> memoList) {
        this.memoList = memoList;
        data = gson.toJson(memoList);
        session.putSessionValue(SessionManager.KEY_MEMO, data);
    }

}
