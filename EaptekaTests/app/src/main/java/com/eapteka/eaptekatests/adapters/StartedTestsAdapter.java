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

public class StartedTestsAdapter extends RecyclerView.Adapter<StartedTestsAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private ArrayList<Test> tests;
    private OnTestListener onTestListener;

    public StartedTestsAdapter(Context context, OnTestListener onStockListener, ArrayList<Test> tests) {
        this.inflater = LayoutInflater.from(context);
        this.tests = tests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.started_test, parent, false);
        return new StartedTestsAdapter.ViewHolder(view, onTestListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Test test = tests.get(position);
        holder.stockTickerText.setText(test.getTitle());
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public Test getTestsElement(int position) {
        return tests.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView stockTickerText;
        OnTestListener onTestListener;

        public ViewHolder(View view, OnTestListener onTestListener) {
            super(view);
            stockTickerText = view.findViewById(R.id.med_name);
            this.onTestListener = onTestListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTestListener.onPopularStockClick(v, getAdapterPosition());
        }
    }

    public interface OnTestListener {
        void onPopularStockClick(View view, int position);
    }
}
