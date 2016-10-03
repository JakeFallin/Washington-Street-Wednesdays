package com.jakefallin.wsw;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.R.id.list;

/**
 * Created by JakeFallin on 9/30/2016.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    private List<Restaurant> list;

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public MyViewHolder(View view) {

            super(view);
            title = (TextView) view.findViewById(R.id.title);

        }
    }

    public RestaurantAdapter(List<Restaurant> l) {
        this.list = l;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Restaurant s = list.get(position);
        holder.title.setText(s.getTitle());

    }

    public void updateList(List<Restaurant> data) {
        list = data;
    }

}
