package dev.daniel.memoraise.Interface;

import java.util.ArrayList;

import dev.daniel.memoraise.Model.MemoModel;

public interface IMemo {
    void initAdapter(ArrayList<MemoModel> memoList);
    void insertAdapter(String title, String description);
    void updateAdapter(String title, String description, int position);
    void removeAdapter(int position);
    void openUpdateDialog(int position);
}
