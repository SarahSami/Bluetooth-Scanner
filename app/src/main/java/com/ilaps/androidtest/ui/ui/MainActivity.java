package com.ilaps.androidtest.ui.ui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ilaps.androidtest.ui.adapter.BluetoothDevicesAdapter;

/**
 * Created by Sarah on 7/12/17.
 */
public class MainActivity extends BaseActivity {

    private ProgressBar progressBar;
    private BluetoothDevicesAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView emptyListViewMsg;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(bluetoothDiscoveryReceiver, filter);
        bluetoothAdapter.startDiscovery();



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(bluetoothDiscoveryReceiver);
        super.onDestroy();
    }


    private final BroadcastReceiver bluetoothDiscoveryReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                progressBar.setVisibility(View.VISIBLE);

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                if(progressBar != null)
                    progressBar.setVisibility(View.GONE);

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d("device found", device.getName());
            }
        }
    };

}
