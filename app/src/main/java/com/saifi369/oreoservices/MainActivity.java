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
    public static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //this method schedules the job
    public void scheduleService(View view){

        JobScheduler jobScheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName= new ComponentName(this,MyDownloadJob.class);
        JobInfo jobInfo=new JobInfo.Builder(JOB_ID,componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setMinimumLatency(0)
                .setPersisted(true)
                .build();

        int result = jobScheduler.schedule(jobInfo);

        if(result == JobScheduler.RESULT_SUCCESS)
            Log.d(TAG, "scheduleService: Job Scheduled");
        else
            Log.d(TAG, "scheduleService: Job not scheduled");


    }

    //this method cancels the scheduled job
    public void cancelService(View view){

        JobScheduler jobScheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(JOB_ID);
        Log.d(TAG, "cancelService: job cancelled");
    }


}
