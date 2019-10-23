package com.flowplanner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JobFragment extends Fragment {

    private EditText jobNameET, startTimeET, deadlineET;
    private Button saveBtn;
    private Spinner spinner;
    private View view;
    private String pName;

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
        actionBar.setTitle(R.string.create_job);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_job_manager,container,false);

        pName = getArguments().getString(PlanListItemAdapter.planName);


        jobNameET = view.findViewById(R.id.jobNameET);
        startTimeET = view.findViewById(R.id.startTimeET);
        deadlineET = view.findViewById(R.id.deadlineET);
        saveBtn = view.findViewById(R.id.saveButtonJob);
        spinner = view.findViewById(R.id.spinner);

        SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(getContext(),R.array.Times, android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerAdapter);

        startTimeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int mount = mcurrentTime.get(Calendar.MONTH);
                int day = mcurrentTime.get(Calendar.DATE);
                final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                final int minute = mcurrentTime.get(Calendar.MINUTE);

                final String[] date = {""};

                DatePickerDialog dpd = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month += 1;
                                date[0] = dayOfMonth + "/" + month + "/" + year +" ";

                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        startTimeET.setText(date[0] + selectedHour + ":" + selectedMinute);
                                    }
                                }, hour, minute, true);//Yes 24 hour time
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }, year, mount, day);
                dpd.show();
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jobName = jobNameET.getText().toString();
                String startTime = startTimeET.getText().toString();
                String deadline = deadlineET.getText().toString();
                String deadlineTime = spinner.getSelectedItem().toString();

                JobEntity job = new JobEntity(pName,jobName,startTime,deadline, deadlineTime);

                AppDatabase.getDatabase(getContext()).jobDao().insert(job);
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
