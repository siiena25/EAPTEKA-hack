package com.eapteka.eaptekatests.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.ArrayList;

public class Holder extends RecyclerView.Adapter<Holder.ViewHolder> {
    private final LayoutInflater inflater;
    private final ArrayList<Test> tests;
    private final OnFinishedTestListener onFinishedTestListener;

    public Holder(Context context, OnFinishedTestListener onFinishedTestListener, ArrayList<Test> tests) {
        this.inflater = LayoutInflater.from(context);
        this.tests = tests;
        this.onFinishedTestListener = onFinishedTestListener;
    }

    @NonNull
    @Override
    public Holder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.finished_test, parent, false);
        return new Holder.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder.ViewHolder holder, int position) {
        Test test = tests.get(position);
        holder.testTitle.setText(test.getTitle());
        holder.itemView.setOnClickListener(v -> {
            onFinishedTestListener.onFinishedTestClick(holder.itemView, position);
        });
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public Test getTestsElement(int position) {
        return tests.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView testTitle;

        public ViewHolder(View view) {
            super(view);
            testTitle = view.findViewById(R.id.med_name);
        }

    }

    public interface OnFinishedTestListener {
        void onFinishedTestClick(View view, int position);
    }
}

