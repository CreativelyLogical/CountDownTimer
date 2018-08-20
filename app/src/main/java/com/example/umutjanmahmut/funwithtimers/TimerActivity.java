package com.example.umutjanmahmut.funwithtimers;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TimerActivity extends AppCompatActivity {

    Button resumePause;
    TextView timeDisplay;

    private long mTimeLeftInMillis;

    private boolean mTimerRunning = false;

    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        resumePause = (Button) findViewById(R.id.resumePause);
        timeDisplay = (TextView) findViewById(R.id.timeDisplay);

        resumePause.setText("Pause");

        Intent receiver = getIntent();
        final int seconds = receiver.getIntExtra("countDownSeconds", 0);
        toastMessage("Count down is " + seconds + " seconds");

        mTimeLeftInMillis = (long) seconds * 1000;

        startTimer();

        resumePause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


//                timeDisplay.setText(seconds + " seconds");
                if (!mTimerRunning) {
                    startTimer();

                }
                else if (mTimerRunning) {
                    pauseTimer();
                }

            }
        });
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                setText();
            }

            @Override
            public void onFinish() {
                timeDisplay.setText("Done!");
                Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backToMain);
            }
        }.start();
        mTimerRunning = true;
        resumePause.setText("pause");
    }

    private void pauseTimer() {
        if (mTimerRunning) {
            mCountDownTimer.cancel();
            mTimerRunning = false;
        }
        resumePause.setText("resume");
    }

    private void setText() {
        timeDisplay.setText("" + mTimeLeftInMillis/1000);
    }

    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
