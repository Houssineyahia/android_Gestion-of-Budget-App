package com.example.myaap_gestion_of_budget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuView extends RelativeLayout {
    private TextView label;
    private TextView theprice;



    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void findViews(){
        label = (TextView) findViewById(R.id.item_nom);
        theprice = (TextView) findViewById(R.id.leprix);

    }
    public void display(final MenuClass Menu){
        label.setText(Menu.getNommenu());
        theprice.setText(Menu.getPrix());

    }


    public static MenuView create(ViewGroup parent){
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        MenuView itemView = (MenuView) li.inflate(R.layout.custommenu, parent, false);
        itemView.findViews();
        return itemView;
    }
}

