package dev.daniel.memoraise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.daniel.memoraise.Interface.IMemo;
import dev.daniel.memoraise.Model.MemoModel;
import dev.daniel.memoraise.R;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    private Context context;
    private ArrayList<MemoModel> memoList;
    private IMemo adapterCallback;

    public MemoAdapter(Context context, ArrayList<MemoModel> memoList, IMemo adapterCallback) {
        this.context = context;
        this.memoList = memoList;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_memo, parent, false);
        return new MemoViewHolder(view, adapterCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        MemoModel currentMemo = memoList.get(position);
        holder.tvTitle.setText(currentMemo.getMemoTitle());
        holder.tvDescription.setText(currentMemo.getMemoDescription());
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    public static class MemoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvDescription;
        private IMemo adapterCallback;

        private MemoViewHolder(@NonNull View itemView, IMemo adapterCallback) {
            super(itemView);
            this.adapterCallback = adapterCallback;
            tvTitle = itemView.findViewById(R.id.item_text_view_title);
            tvDescription = itemView.findViewById(R.id.item_text_view_description);
            ImageView btnEdit = itemView.findViewById(R.id.button_edit);
            ImageView btnDelete = itemView.findViewById(R.id.button_delete);
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_edit:
                    adapterCallback.openUpdateDialog(getAdapterPosition());
                    break;
                case R.id.button_delete:
                    adapterCallback.removeAdapter(getAdapterPosition());
                    break;
            }
        }
    }

    public void insertItem(String title, String description) {
        memoList.add(new MemoModel(title, description));
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        memoList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, memoList.size());
    }

    public void updateItem(String title, String description, int position) {
        memoList.set(position, new MemoModel(title, description));
        notifyItemChanged(position);
    }

    public MemoModel getMemoDetail(int position) {
        return memoList.get(position);
    }

    public ArrayList<MemoModel> getMemoList() {
        return memoList;
    }
}
