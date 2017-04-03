package com.vegantravels.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vegantravels.model.Cruises;

import java.util.ArrayList;

/**
 * Created by Rakib on 4/3/2017.
 */

public class CruisesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Cruises> cruisesList;

    public CruisesAdapter(Context context, ArrayList<Cruises> cruisesList) {
        this.context = context;
        this.cruisesList = cruisesList;
    }

    @Override
    public int getCount() {
        return cruisesList.size();
    }

    @Override
    public Object getItem(int i) {
        return cruisesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
