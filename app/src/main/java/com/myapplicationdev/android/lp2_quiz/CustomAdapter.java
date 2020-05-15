package com.myapplicationdev.android.lp2_quiz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<TODO> data;
    public CustomAdapter(Activity activity, ArrayList<TODO> data){
        this.activity=activity;
        this.data=data;
    }

    @Override
    public int getCount() { return data.size();}

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View myview;
        if(view==null){
            myview=new View(activity);
            LayoutInflater lf =(LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
            myview=lf.inflate(R.layout.row,null);
            TextView tvid = myview.findViewById(R.id.tv2);
            TextView tvdate = myview.findViewById(R.id.tv3);
            TextView tvdata = myview.findViewById(R.id.tv4);
            tvid.setText(String.valueOf(data.get(pos).getId()));
            tvdate.setText(data.get(pos).getDate());
            tvdata.setText(data.get(pos).getData());

            return myview;


        }else
        {
            return myview=view;
        }
    }


}
