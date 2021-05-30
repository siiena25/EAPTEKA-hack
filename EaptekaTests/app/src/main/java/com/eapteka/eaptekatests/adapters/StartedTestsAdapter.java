package com.eapteka.eaptekatests.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.test.InfoVM;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.ArrayList;

public class StartedTestsAdapter extends RecyclerView.Adapter<StartedTestsAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final ArrayList<Test> tests;
    private final OnStartedTestListener onStartedTestListener;
    private Context context;

    public StartedTestsAdapter(Context context, OnStartedTestListener onStartedTestListener, ArrayList<Test> tests) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.tests = tests;
        this.onStartedTestListener = onStartedTestListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.started_test, parent, false);
        return new StartedTestsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Test test = tests.get(position);
        holder.testTitle.setText(test.getTitle());

        InfoVM viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(InfoVM.class);
        holder.itemView.setOnClickListener(v -> {
            onStartedTestListener.onStartedTestClick(v, position);
            viewModel.name.setValue(test.getTitle());
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

    public interface OnStartedTestListener {
        void onStartedTestClick(View v, int adapterPosition);
    }
}
