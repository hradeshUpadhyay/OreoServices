package com.saifi369.oreoservices;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyDownloadJob extends JobService {

    public static final String TAG = "MyTag";
    public static final String SEVICE_MESSAGE = "SeviceMessage";

    public MyDownloadJob() {
    }

    @Override
    public boolean onStartJob(final JobParameters params) {

        Log.d(TAG, "onStartJob: "+params.getJobId());
        Log.d(TAG, "onStartJob: Thread Name: "+Thread.currentThread().getName());

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: Download completed");

                LocalBroadcastManager.getInstance(getApplicationContext())
                        .sendBroadcast(new Intent(SEVICE_MESSAGE));

                jobFinished(params,false);
            }
        };

        Thread thread=new Thread(runnable);
        thread.start();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


}
