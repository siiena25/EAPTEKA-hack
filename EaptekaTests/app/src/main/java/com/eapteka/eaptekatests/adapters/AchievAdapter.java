package com.eapteka.eaptekatests.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eapteka.eaptekatests.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AchievAdapter extends RecyclerView.Adapter<AchievAdapter.ViewHolder> {
    private ArrayList<String> achievList = new ArrayList<>();

    public AchievAdapter() {
        achievList.add("Первый тест!");
        achievList.add("Знаток Фенозепана");
        achievList.add("Ответственный подход");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.testTitle.setText(achievList.get(position));

    }


    @Override
    public int getItemCount() {
        return achievList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView testTitle;

        public ViewHolder(View view) {
            super(view);
            testTitle = view.findViewById(R.id.med_name);
        }

    }
}
