package com.example.readquizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readquizapp.model.Reading;

import java.util.List;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ReadingViewHolder> {

    private List<Reading> readingList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Reading reading);
    }

    public ReadingAdapter(List<Reading> readingList, OnItemClickListener listener) {
        this.readingList = readingList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReadingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reading, parent, false);
        return new ReadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingViewHolder holder, int position) {
        Reading reading = readingList.get(position);
        holder.tvTitle.setText(reading.getTitle());
        holder.tvSummary.setText(reading.getSummary());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(reading));
    }

    @Override
    public int getItemCount() {
        return readingList.size();
    }

    public static class ReadingViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSummary;

        public ReadingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSummary = itemView.findViewById(R.id.tvSummary);
        }
    }
}
