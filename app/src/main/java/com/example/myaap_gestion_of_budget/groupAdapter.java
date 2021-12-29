package com.example.myaap_gestion_of_budget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class groupAdapter extends RecyclerView.Adapter<groupAdapter.groupViewHolder> {

    Context context;
    ArrayList<GroupClass> grouparrayList;
    private groupViewHolder.OnclickListener gonclicklister;
    public groupAdapter(Context context, ArrayList<GroupClass> grouparrayList , groupViewHolder.OnclickListener gonclicklister) {
        this.context = context;
        this.grouparrayList = grouparrayList;
        this.gonclicklister = gonclicklister;
    }

    @NonNull
    @Override
    public groupAdapter.groupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.groupitem,parent ,false);

        return new groupViewHolder(v , gonclicklister);
    }

    @Override
    public void onBindViewHolder(@NonNull groupAdapter.groupViewHolder holder, int position) {
        GroupClass group = grouparrayList.get(position);

        holder.Title.setText(group.getTitle());
        holder.Admin.setText(group.getAdmin());

    }

    @Override
    public int getItemCount() {
        return grouparrayList.size();
    }


    public static class groupViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView Title , Admin;
        OnclickListener ongroupclick;

        public groupViewHolder(@NonNull View itemView , OnclickListener ongroupclick) {
            super(itemView);
            Title = itemView.findViewById(R.id.GroupName);
            Admin = itemView.findViewById(R.id.creator);
            this.ongroupclick = ongroupclick;


            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            ongroupclick.GroupItemClick(getAdapterPosition());
        }

        public interface  OnclickListener{
            void GroupItemClick(int position);
        }
    }

}
