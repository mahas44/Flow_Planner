package com.flowplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobListItemAdapter extends RecyclerView.Adapter<JobListItemAdapter.JobListItemViewHolder> {


    private Context context;
    private List<JobEntity> jobArrayList;
    private JobEntity job;

    JobListItemAdapter(Context context, List<JobEntity> jobArrayList) {
        this.context = context;
        this.jobArrayList = jobArrayList;
    }

    @NonNull
    @Override
    public JobListItemAdapter.JobListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.job_list, parent,false);
        return new JobListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobListItemAdapter.JobListItemViewHolder holder, final int position) {

        job = jobArrayList.get(position);
        holder.name.setText(String.format(" : %s", job.getName()));
        holder.startTime.setText(String.format(" : %s",job.getStartTime()));
        holder.deadline.setText(String.format(" : %s",job.getDeadline()) +" "+ job.getDeadlineTime());

        holder.deleteJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase.getDatabase(context).jobDao().delete(job);
                jobArrayList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobArrayList.size();
    }

    class JobListItemViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView name, startTime, deadline;
        ImageView deleteJob;

        JobListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.jobListCV);
            name = itemView.findViewById(R.id.jobListNameTV);
            startTime = itemView.findViewById(R.id.jobListStartTimeTV);
            deadline = itemView.findViewById(R.id.jobListDeadlineTV);
            deleteJob = itemView.findViewById(R.id.deleteJobIV);
        }
    }
}
