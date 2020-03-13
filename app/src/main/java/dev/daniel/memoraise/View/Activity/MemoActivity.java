package dev.daniel.memoraise.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

import dev.daniel.memoraise.Adapter.MemoAdapter;
import dev.daniel.memoraise.Interface.IMemo;
import dev.daniel.memoraise.Model.MemoModel;
import dev.daniel.memoraise.Presenter.MemoPresenter;
import dev.daniel.memoraise.R;
import dev.daniel.memoraise.View.Fragment.InputMemoDialog;
import dev.daniel.memoraise.View.Fragment.UpdateMemoDialog;

public class MemoActivity extends AppCompatActivity implements IMemo, View.OnClickListener {

    private RecyclerView recycler;
    private MemoAdapter adapter;
    private MemoPresenter presenter;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
        recycler = findViewById(R.id.recycler_memo);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        tvEmpty = findViewById(R.id.text_view_empty);
        FloatingActionButton btnAdd = findViewById(R.id.button_add);
        btnAdd.setOnClickListener(this);
        presenter = new MemoPresenter(this, this);
        presenter.initMemoData();
    }

    @Override
    public void initAdapter(ArrayList<MemoModel> memoList) {
        if (memoList.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        }
        adapter = new MemoAdapter(this, memoList, this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void insertAdapter(String title, String description) {
        adapter.insertItem(title, description);
        presenter.modifyMemoData(adapter.getMemoList());
        if (!adapter.getMemoList().isEmpty()) {
            tvEmpty.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void updateAdapter(String title, String description, int position) {
        adapter.updateItem(title, description, position);
        presenter.modifyMemoData(adapter.getMemoList());
    }


    @Override
    public void removeAdapter(int position) {
        adapter.removeItem(position);
        presenter.modifyMemoData(adapter.getMemoList());
        if (adapter.getMemoList().isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        }
    }

    @Override
    public void openUpdateDialog(int position) {
        MemoModel currentMemo = adapter.getMemoDetail(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("memo", currentMemo);
        bundle.putInt("position", position);
        UpdateMemoDialog dialog = new UpdateMemoDialog();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onClick(View v) {
        InputMemoDialog dialog = new InputMemoDialog();
        dialog.show(getSupportFragmentManager(), null);
    }
}