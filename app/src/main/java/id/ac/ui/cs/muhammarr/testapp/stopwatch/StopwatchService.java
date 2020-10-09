package id.ac.ui.cs.muhammarr.testapp.stopwatch;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.concurrent.TimeUnit;

// https://developer.android.com/guide/components/bound-services
public class StopwatchService extends Service {
    private final IBinder mBinder = new LocalBinder();

    //Source: https://stackoverflow.com/q/8255738
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }


    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }

    //elaspsed time in milliseconds
    public long[] getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = (System.currentTimeMillis() - startTime);
        } else {
            elapsed = (stopTime - startTime);
        }
        long hours = TimeUnit.MILLISECONDS.toHours(elapsed);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsed) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsed) % 60;
        long milis = elapsed % 1000;
        long[] result = {hours, minutes, seconds, milis};
        return result;
    }

    public class LocalBinder extends Binder {
        public StopwatchService getService() {
            return StopwatchService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
