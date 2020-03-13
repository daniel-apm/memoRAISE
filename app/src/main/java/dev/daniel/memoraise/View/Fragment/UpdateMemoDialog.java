package dev.daniel.memoraise.View.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import dev.daniel.memoraise.Interface.IMemo;
import dev.daniel.memoraise.Model.MemoModel;
import dev.daniel.memoraise.R;

public class UpdateMemoDialog extends DialogFragment implements View.OnClickListener {

    private EditText etTitle, etDescription;
    private MemoModel memo;
    private IMemo dialogCallback;
    private int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update_memo, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            memo = (MemoModel) bundle.getSerializable("memo");
            position = bundle.getInt("position");
        }
        Button btnUpdate = view.findViewById(R.id.button_update);
        etTitle = view.findViewById(R.id.edit_text_title);
        etDescription = view.findViewById(R.id.edit_text_description);
        etTitle.setText(memo.getMemoTitle());
        etDescription.setText(memo.getMemoDescription());
        btnUpdate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        dialogCallback.updateAdapter(title, description, position);
        dismiss();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogCallback = (IMemo) context;
        } catch (Exception e) {
            throw new RuntimeException(context.toString() + " must implement IMemo");
        }
    }
}
