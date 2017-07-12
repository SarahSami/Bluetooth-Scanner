package com.ilaps.androidtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ilaps.androidtest.R;

import java.util.List;


/**
 * Created by Sarah on 7/12/17.
 */

public class BluetoothDevicesAdapter extends RecyclerView.Adapter<BluetoothDevicesAdapter.ViewHolder> {
    private List<String> devicesName;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;

        public ViewHolder(View v) {
            super(v);
            nameTv = (TextView) itemView.findViewById(R.id.device_name);
        }
    }

    public BluetoothDevicesAdapter(Context context, List<String> data) {
        devicesName = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_device, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameTv.setText(devicesName.get(position));

    }


    @Override
    public int getItemCount() {
        return devicesName.size();
    }

}