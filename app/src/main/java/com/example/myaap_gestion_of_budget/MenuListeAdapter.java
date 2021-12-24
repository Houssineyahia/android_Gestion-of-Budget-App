package com.example.myaap_gestion_of_budget;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MenuListeAdapter extends ArrayAdapter<com.example.myaap_gestion_of_budget.MenuClass> {


    public MenuListeAdapter( Context context , ArrayList<com.example.myaap_gestion_of_budget.MenuClass> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        com.example.myaap_gestion_of_budget.MenuView myview = (com.example.myaap_gestion_of_budget.MenuView) convertView;
        if (myview == null) {
            myview = myview.create(parent);
        }

        myview.display(super.getItem(position));
        return myview;
    }

}
