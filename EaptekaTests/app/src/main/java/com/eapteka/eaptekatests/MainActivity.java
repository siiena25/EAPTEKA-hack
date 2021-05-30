package com.eapteka.eaptekatests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;

import com.eapteka.eaptekatests.database.Logger;
import com.eapteka.eaptekatests.database.Pill;
import com.eapteka.eaptekatests.database.PillViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PillViewModel pillViewModel;
    ArrayList<Pill> pills;
    Pill pill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pillViewModel = new ViewModelProvider(this).get(PillViewModel.class);

        Observer<ArrayList<Pill>> observer = pills -> {
            if (pills != null && pills.size() != 0) {
                this.pills = pills;
                for(int i = 0; i < pills.size(); i++) {
                    Pill pill = pills.get(i);
                }
            }
        };
        Observer<Pill> obs = pill -> {
            if (pill != null) {
                this.pill = pill;
            }
        };

        pillViewModel.updatePill("Arbidol");
        pillViewModel.getPill().observe(this, obs);
    }
}
