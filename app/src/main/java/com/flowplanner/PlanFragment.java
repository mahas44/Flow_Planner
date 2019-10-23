package com.flowplanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class PlanFragment extends Fragment {

    private View view;
    private EditText planNameET, descriptionET, categoryET;
    private Button saveBtn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle(R.string.create_plan);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_plan_manager,container,false);
        planNameET = view.findViewById(R.id.planNameET);
        categoryET = view.findViewById(R.id.categoryET);
        descriptionET = view.findViewById(R.id.descriptionET);
        saveBtn = view.findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String planName = planNameET.getText().toString();
                String category = categoryET.getText().toString();
                String description = descriptionET.getText().toString();

                PlanEntity plan = new PlanEntity(planName,description,category);

                AppDatabase.getDatabase(getContext()).planDao().insert(plan);
                goBack();
            }
        });


        return view;
    }

    private void goBack(){
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }
}
