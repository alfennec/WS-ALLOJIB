package com.fennec.allojib.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.fennec.allojib.R;

public class Wait_Activity extends AppCompatActivity {

    int progress = 0;
    int finish_progress = 100;
    ProgressBar progressBar;
    private int i = 0;

    private Handler hdlr = new Handler();

    Wait_Activity main;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        main = this;

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        i = progressBar.getProgress();
        new Thread(new Runnable()
        {
            public void run()
            {
                while (i < 50)
                {
                    i += 1;
                    hdlr.post(new Runnable()
                    {
                        public void run()
                        {
                            progressBar.setProgress(i);
                        }
                    });

                    try {
                        // Sleep for 100 milliseconds to show the progress slowly.
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                main.finish();
            }
        }).start();
    }


}
