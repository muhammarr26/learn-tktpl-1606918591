package id.ac.ui.cs.muhammarr.testapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.ac.ui.cs.muhammarr.testapp.stopwatch.StopwatchService;

public class MainActivity extends AppCompatActivity {

    private StopwatchService stopwatch;
    private Handler handler = new Handler();
    private TextView hours;
    private TextView minutes;
    private TextView seconds;
    private TextView milis;
    boolean mBound = false;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            StopwatchService.LocalBinder binder = (StopwatchService.LocalBinder) service;
            stopwatch = binder.getService();
            mBound = true;
            if(stopwatch.isRunning()) handler.postDelayed(runnable, 1);
            Log.println(Log.INFO,"Stopwatch","service bounded");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            Log.println(Log.INFO,"Stopwatch","service disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hours = findViewById(R.id.hours);
        minutes = findViewById(R.id.minutes);
        seconds = findViewById(R.id.seconds);
        milis = findViewById(R.id.milis);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Log.println(Log.INFO,"Stopwatch","binding service");
        Intent intent = new Intent(this, StopwatchService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    // Source: https://stackoverflow.com/a/7240268
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void toggleStopwatch(View view) {
        Log.println(Log.INFO,"Stopwatch","clicked");
        if (mBound) {
            // Call a method from the LocalService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            if (stopwatch.isRunning()){
                Log.println(Log.INFO,"Stopwatch","Stop Stopwatch Service");
                Intent intent = new Intent(getApplicationContext(), StopwatchService.class);
                stopwatch.stop();
                ((Button) view).setText(getString(R.string.start));
                handler.removeCallbacks(runnable);
            } else {
                Log.println(Log.INFO,"Stopwatch","Start Stopwatch Service");
                Intent intent = new Intent(getApplicationContext(), StopwatchService.class);
                stopwatch.start();
                ((Button) view).setText(getString(R.string.stop));
                handler.postDelayed(runnable, 1);
            }
        }

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long[] result = stopwatch.getElapsedTime();
            hours.setText(String.format("%d", result[0]));
            minutes.setText(String.format("%d", result[1]));
            seconds.setText(String.format("%d", result[2]));
            milis.setText(String.format("%d", result[3]));
            handler.postDelayed(this, 1);
//            Log.println(Log.INFO,"Stopwatch","update");
        }
    };

    @Override
    protected void onStop() {
        Log.println(Log.INFO,"Stopwatch","unbind service");
        super.onStop();
        unbindService(connection);
        mBound = false;
        handler.removeCallbacks(runnable);
    }

}