package com.ilaps.androidtest.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Sarah on 7/11/17.
 */

public class BluetoothDevicesAdapter extends RecyclerView.Adapter<BluetoothDevicesAdapter.ViewHolder> {
    private List<City> cities;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cityNameView, countryNameView, removeCityView;
        public CardView cardView;

        public ViewHolder(View v) {
            super(v);
            cityNameView = (TextView) itemView.findViewById(R.id.city_name);
            countryNameView = (TextView) itemView.findViewById(R.id.country_name);
            removeCityView = (TextView) itemView.findViewById(R.id.remove_city);
            cardView = (CardView) itemView.findViewById(R.id.card_view);

        }
    }

    public BluetoothDevicesAdapter(Context context, List<City> data) {
        cities = data;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_city, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.cityNameView.setText(cities.get(position).getName());
        holder.countryNameView.setText(cities.get(position).getCountry());

    }


    @Override
    public int getItemCount() {
        return cities.size();
    }

}