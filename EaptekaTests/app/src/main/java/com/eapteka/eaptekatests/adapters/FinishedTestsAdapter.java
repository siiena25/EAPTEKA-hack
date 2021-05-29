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

public class FinishedTestsAdapter extends RecyclerView.Adapter<FinishedTestsAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final ArrayList<Test> tests;
    private final OnFinishedTestListener onFinishedTestListener;

    public FinishedTestsAdapter(Context context, OnFinishedTestListener onFinishedTestListener, ArrayList<Test> tests) {
        this.inflater = LayoutInflater.from(context);
        this.tests = tests;
        this.onFinishedTestListener = onFinishedTestListener;
    }

    @NonNull
    @Override
    public FinishedTestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.finished_test, parent, false);
        return new FinishedTestsAdapter.ViewHolder(view, onFinishedTestListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FinishedTestsAdapter.ViewHolder holder, int position) {
        Test test = tests.get(position);
        holder.testTitle.setText(test.getTitle());
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public Test getTestsElement(int position) {
        return tests.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView testTitle;
        OnFinishedTestListener onFinishedTestListener;

        public ViewHolder(View view, OnFinishedTestListener onFinishedTestListener) {
            super(view);
            testTitle = view.findViewById(R.id.med_name);
            this.onFinishedTestListener = onFinishedTestListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFinishedTestListener.onFinishedTestClick(v, getAdapterPosition());
        }
    }

    public interface OnFinishedTestListener {
        void onFinishedTestClick(View view, int position);
    }
}

