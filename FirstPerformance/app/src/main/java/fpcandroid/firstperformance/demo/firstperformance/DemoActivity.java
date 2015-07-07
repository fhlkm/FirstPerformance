package fpcandroid.firstperformance.demo.firstperformance;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mtrussell on 6/30/15.
 * Copyright First Performance Corporation
 *
 * This activity displays a text view and two buttons.
 * One of the buttons changes the window's background color.
 * Clicking the other button should begin a countdown from 100 to 1 and
 * display the count down in the text view.
 * Both buttons must be able to operate simultaneously.
 */
public class DemoActivity extends Activity implements DemoInterface {

    private TextView _countdownText;
    private Button mColorButton;
    private Button mCountButton;
    private DemoAsyncTask mTask= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // DO NOT EDIT THIS METHOD
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        _countdownText = (TextView) findViewById(R.id.countdownText);
        mColorButton = (Button) findViewById(R.id.colorButton);
        mCountButton = (Button) findViewById(R.id.countdownButton);

        mColorButton.setOnClickListener(mListener);
        mCountButton.setOnClickListener(mListener);

        mTask = new DemoAsyncTask(this);

    }


    public void countdownButtonClicked(View view) {
        view.setEnabled(false);

        // 1) Begin the countdown when the countdown button is clicked

    }

    View.OnClickListener mListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.colorButton:
                    Log.i("Button", "R.id.colorButton");
                    colorButtonClicked(v);
                    _colorIndex = (_colorIndex + 1) % 4;
                    break;
                case R.id.countdownButton:
                    Log.i("Button", "R.id.countdownButton");
                    if(mTask.getStatus()== AsyncTask.Status.PENDING){
                        mTask.execute();
                    }else if(mTask.getStatus()==AsyncTask.Status.FINISHED){
                        mTask = new DemoAsyncTask(DemoActivity.this);
                        mTask.execute();
                    }
                default:
                    break;
            }

        }
    };

    @Override
    public void setCountdownText(Integer count) {

        // 2) Update the text view with the countdown progress
        _countdownText.setText(count.toString());

    }


    // DO NOT EDIT BELOW THIS LINE
    private int _colorIndex = 0;
    public void colorButtonClicked(View view) {
        switch (_colorIndex) {
            case 0:
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                break;
            case 1:
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                break;
            case 2:
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                break;
            default:
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                break;
        }

        _colorIndex = (_colorIndex < 2) ? _colorIndex + 1 : 0;
    }

}