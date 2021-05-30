package com.eapteka.eaptekatests.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.eapteka.eaptekatests.R;
import com.eapteka.eaptekatests.test.InfoVM;
import com.eapteka.eaptekatests.test_models.Test;

import java.util.ArrayList;

public class FinishedTestsAdapter extends RecyclerView.Adapter<FinishedTestsAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final ArrayList<Test> tests;
    private final OnFinishedTestListener onFinishedTestListener;
    private Context context;

    public FinishedTestsAdapter(Context context, OnFinishedTestListener onFinishedTestListener, ArrayList<Test> tests) {
        this.inflater = LayoutInflater.from(context);
        this.tests = tests;
        this.onFinishedTestListener = onFinishedTestListener;
        this.context = context;
    }

    @NonNull
    @Override
    public FinishedTestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.finished_test, parent, false);

        return new FinishedTestsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FinishedTestsAdapter.ViewHolder holder, int position) {
        Test test = tests.get(position);
        holder.testTitle.setText(test.getTitle());
        View view = holder.itemView;
        int picture;
        switch (test.getTitle()) {
            case "Но-Шпа" :
                picture = R.drawable.noshpa;
                break;
            case "Мезим" :
                picture = R.drawable.mezim;
                break;
            case "Арбидол" :
                picture = R.drawable.arbidol;
                break;
            default: picture = R.drawable.arbidol;
        }
        ((ImageView) view.findViewById(R.id.character_post_image)).setImageResource(picture);

        InfoVM viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(InfoVM.class);
        holder.itemView.setOnClickListener(v -> {
            onFinishedTestListener.onFinishedTestClick(holder.itemView, position);
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

    public interface OnFinishedTestListener {
        void onFinishedTestClick(View view, int position);
    }
}

