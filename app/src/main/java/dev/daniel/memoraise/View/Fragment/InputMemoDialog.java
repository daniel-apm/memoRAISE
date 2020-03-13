package dev.daniel.memoraise.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import dev.daniel.memoraise.Interface.IMemo;
import dev.daniel.memoraise.R;

public class InputMemoDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private EditText etTitle, etDescription;
    private IMemo inputCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_input_memo, container, false);
        etTitle = view.findViewById(R.id.edit_text_title);
        etDescription = view.findViewById(R.id.edit_text_description);
        Button btnAdd = view.findViewById(R.id.button_add);
        btnAdd.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        if (title.equals("") || description.equals("")) {
            Toast.makeText(getContext(), "All field must be filled", Toast.LENGTH_SHORT).show();
        } else {
            inputCallback.insertAdapter(title, description);
            dismiss();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            inputCallback = (IMemo) context;
        } catch (Exception e) {
            throw new RuntimeException(context.toString() + " must implement IMemo");
        }
    }
}
