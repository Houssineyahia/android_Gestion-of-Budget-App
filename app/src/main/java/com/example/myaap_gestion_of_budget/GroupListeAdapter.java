package com.example.myaap_gestion_of_budget;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GroupListeAdapter  extends ArrayAdapter<GroupClass>{


    public GroupListeAdapter( Context context , ArrayList<GroupClass> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Groupview myview = (Groupview) convertView;
        if (myview == null) {
            myview = myview.create(parent);
        }

        myview.display(super.getItem(position));
        return myview;
    }

}
