package com.example.myaap_gestion_of_budget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuView extends RelativeLayout {
    private TextView label;
    private TextView theprice;
    private TextView date;
    private TextView whobuy;
    private  TextView comment;
    private ImageView image;


    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void findViews(){
        label = (TextView) findViewById(R.id.item_nom);
        theprice = (TextView) findViewById(R.id.leprix);
        date = (TextView) findViewById(R.id.date);
        whobuy = (TextView) findViewById(R.id.whobuy);
        comment = (TextView) findViewById(R.id.comment);
        image=(ImageView) findViewById(R.id.imageView);
    }
    public void display(final MenuClass Menu){
        label.setText(Menu.getNommenu());
      if(Menu.getNommenu().equals("Food")){
            image.setImageDrawable(getResources().getDrawable(R.drawable.food));
        }else if(Menu.getNommenu().equals("transport")){
            image.setImageDrawable(getResources().getDrawable(R.drawable.transport));
        }else if(Menu.getNommenu().equals("others")){
            image.setImageDrawable(getResources().getDrawable(R.drawable.shopping));
        }else if(Menu.getNommenu().equals("cofee")){
          image.setImageDrawable(getResources().getDrawable(R.drawable.emporter));
      }
        theprice.setText(Menu.getPrix());
        date.setText(Menu.getdata());
        whobuy.setText(Menu.getuserexpence());
        comment.setText(Menu.getcomment());

    }


    public static MenuView create(ViewGroup parent){
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        MenuView itemView = (MenuView) li.inflate(R.layout.custommenu, parent, false);
        itemView.findViews();
        return itemView;
    }
}

