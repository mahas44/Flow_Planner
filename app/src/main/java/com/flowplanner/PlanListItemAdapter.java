package com.flowplanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanListItemAdapter extends RecyclerView.Adapter<PlanListItemAdapter.PlanListItemViewHolder> {

    private Context context;
    private List<PlanEntity> planArrayList;
    private boolean includedLayoutControl;

    private List<JobEntity> list;
    private JobListItemAdapter jobListItemAdapter;

    public static String planName = "planName";

    private FragmentManager fragmentManager;


    PlanListItemAdapter(Context context, List<PlanEntity> planArrayList, FragmentManager fragmentManager) {
        this.context = context;
        this.planArrayList = planArrayList;
        this.includedLayoutControl = false;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public PlanListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plan_list, parent, false);
        return new PlanListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlanListItemViewHolder holder, int position) {

        PlanEntity plan = planArrayList.get(position);
        holder.name.setText(String.format(" : %s", plan.getName()));
        holder.category.setText(String.format(" : %s", plan.getCategory()));
        holder.description.setText(String.format(" : %s", plan.getDescription()));
        holder.recyclerView.setVisibility(View.GONE);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!includedLayoutControl){

                    holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    list = AppDatabase.getDatabase(context).jobDao().getJobsFromPlanName(holder.name.getText().toString());
                    jobListItemAdapter = new JobListItemAdapter(context,list);
                    holder.recyclerView.setAdapter(jobListItemAdapter);

                    holder.jobTitle.setVisibility(View.VISIBLE);
                    holder.recyclerView.setVisibility(View.VISIBLE);
                    includedLayoutControl = true;

                } else {
                    holder.jobTitle.setVisibility(View.GONE);
                    holder.recyclerView.setVisibility(View.GONE);
                    includedLayoutControl = false;
                }
            }
        });

//        holder.addNewJob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                intent.putExtra(planName,holder.name.getText().toString());
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return planArrayList.size();
    }


     class PlanListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerView recyclerView;
        CardView cardView;
        TextView name, category, description, jobTitle;
        ImageView addNewJob;

        PlanListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.job_listRV);
            cardView = itemView.findViewById(R.id.planListCV);
            name = itemView.findViewById(R.id.planListNameTV);
            category = itemView.findViewById(R.id.planListCategoryTV);
            description = itemView.findViewById(R.id.planListDescriptionTV);
            jobTitle = itemView.findViewById(R.id.jobsTitle);
            addNewJob = itemView.findViewById(R.id.addNewJobIB);

            addNewJob.setOnClickListener(this);
        }

         @Override
         public void onClick(View view) {
             Bundle bundle = new Bundle();
             bundle.putString(planName, name.getText().toString());
             Fragment jobFragment = new JobFragment();
             jobFragment.setArguments(bundle);
             fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragment_container, jobFragment).commit();
         }
     }

}
