package com.saifi369.oreoservices;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final int JOB_ID = 1001;
    private BroadcastReceiver mReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runCode(View view) {

        JobScheduler jobScheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        JobInfo jobInfo=new JobInfo.Builder(JOB_ID,new ComponentName(this,MyDownloadJob.class))
                .setMinimumLatency(0)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();

        jobScheduler.schedule(jobInfo);


    }

    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mReceiver,new IntentFilter(MyDownloadJob.SEVICE_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mReceiver);
    }

}
