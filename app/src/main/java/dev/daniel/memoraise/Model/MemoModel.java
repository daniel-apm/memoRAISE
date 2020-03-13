package dev.daniel.memoraise.Model;


import java.io.Serializable;

public class MemoModel implements Serializable {
    private String memoTitle;
    private String memoDescription;

    public MemoModel(String memoTitle, String memoDescription) {
        this.memoTitle = memoTitle;
        this.memoDescription = memoDescription;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public String getMemoDescription() {
        return memoDescription;
    }
}
