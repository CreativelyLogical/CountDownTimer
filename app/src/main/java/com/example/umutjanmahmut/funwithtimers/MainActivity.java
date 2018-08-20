package com.example.umutjanmahmut.funwithtimers;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 70000;

    TextView timer;
    Button startPauseResume;
    Button pause;
    EditText input;

//    CountDownTimer countDownTimer;
    private CountDownTimer mCountDownTimer;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private boolean mTimerRunning = false;

    private boolean timeSpecified = false;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);
        startPauseResume = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.stop);
        input = (EditText) findViewById(R.id.editText);
//        input = (EditText) findViewById(R.id.editText);

//        timer.setText((int)mTimeLeftInMillis);
        startPauseResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mTimerRunning) {
                    if (!timeSpecified) {
                        mTimeLeftInMillis = Integer.parseInt(input.getText().toString()) * 1000;
                        timeSpecified = true;
                    }

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
                timer.setText("Done!");
                timeSpecified = false;
             }
         }.start();

         startPauseResume.setText("Pause");
         mTimerRunning = true;
    }

    private void pauseTimer() {
        if (mTimerRunning) {
            mCountDownTimer.cancel();
            mTimerRunning = false;
            startPauseResume.setText("Resume");
        }
        else {

        }
    }

    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void setText() {
        // We just want to print out the seconds for now
//        int timeLeft = (int) mTimeLeftInMillis/1000;
        String format = String.format("%02d:%02d", (int) mTimeLeftInMillis/1000/60, (int) mTimeLeftInMillis/1000%60);
        timer.setText(format);
    }

}
