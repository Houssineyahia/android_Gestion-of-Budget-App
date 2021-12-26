package com.example.myaap_gestion_of_budget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Groupview extends RelativeLayout {
    private TextView label;
    private TextView creator;



    public Groupview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void findViews(){
        label = (TextView) findViewById(R.id.GroupName);
        creator = (TextView) findViewById(R.id.creator);

    }
    public void display(final GroupClass Menu){
        label.setText(Menu.getgroupName());
        creator.setText(Menu.getCreator());

    }


    public static Groupview create(ViewGroup parent){
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        Groupview itemView = (Groupview) li.inflate(R.layout.groupitem, parent, false);
        itemView.findViews();
        return itemView;
    }



}

