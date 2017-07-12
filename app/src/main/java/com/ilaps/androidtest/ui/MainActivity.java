package com.ilaps.androidtest.ui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.ilaps.androidtest.R;
import com.ilaps.androidtest.adapter.BluetoothDevicesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarah on 7/12/17.
 */
public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private BluetoothDevicesAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView emptyListViewMsg;
    private List<String> devicesNames;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        devicesNames = new ArrayList<>();

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(bluetoothDiscoveryReceiver, filter);
        bluetoothAdapter.startDiscovery();

        emptyListViewMsg = (TextView) findViewById(R.id.no_devices);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        updateDevices();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logout() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(bluetoothDiscoveryReceiver);
        super.onDestroy();
    }

    private void checkEmptyList() {
        if (mAdapter.getItemCount() == 0) {
            emptyListViewMsg.setVisibility(View.VISIBLE);
        } else {
            emptyListViewMsg.setVisibility(View.GONE);
        }
    }

    private void updateDevices() {
        devicesNames.add("one");
        devicesNames.add("one");
        devicesNames.add("two");
        devicesNames.add("two");
        devicesNames.add("three");
        devicesNames.add("three");
        devicesNames.add("four");
        devicesNames.add("four");
        devicesNames.add("five");
        devicesNames.add("five");
        devicesNames.add("six");
        devicesNames.add("six");
        devicesNames.add("seven");
        devicesNames.add("seven");
        devicesNames.add("eight");
        devicesNames.add("eight");
        devicesNames.add("nine");
        devicesNames.add("nine");
        devicesNames.add("ten");
        devicesNames.add("ten");
        devicesNames.add("aaa");
        devicesNames.add("bb");
        devicesNames.add("ccc");
        devicesNames.add("ddd");
        devicesNames.add("eee");
        devicesNames.add("offffne");
        devicesNames.add("ggggg");
        devicesNames.add("onhhhhe");

        mAdapter = new BluetoothDevicesAdapter(this, devicesNames);
        mRecyclerView.setAdapter(mAdapter);
        checkEmptyList();
    }


    private final BroadcastReceiver bluetoothDiscoveryReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                progressBar.setVisibility(View.VISIBLE);

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                if (progressBar != null)
                    progressBar.setVisibility(View.GONE);

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d("device found", device.getName());
                devicesNames.add(device.getName());
            }
        }
    };

}
