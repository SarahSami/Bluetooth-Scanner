package com.ilaps.androidtest.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Sarah on 7/11/17.
 */

public class BluetoothDevicesAdapter extends RecyclerView.Adapter<BluetoothDevicesAdapter.ViewHolder> {
    private Context mContext;
    private List<String> devicesName;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;

        public ViewHolder(View v) {
            super(v);

        }
    }

    public BluetoothDevicesAdapter(Context context, List<String> data) {
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

    }


    @Override
    public int getItemCount() {
        return devicesName.size();
    }

}