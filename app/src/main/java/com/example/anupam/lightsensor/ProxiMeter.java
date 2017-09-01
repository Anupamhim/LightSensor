package com.example.anupam.lightsensor;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProxiMeter extends Activity implements SensorEventListener {
    Button back;
    SensorManager mSensorManager;
    Sensor mProxi;
    TextView textView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxi_meter);

        textView =(TextView)findViewById(R.id.textView);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mProxi = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mediaPlayer = MediaPlayer.create(this,R.raw.amader);
        mediaPlayer.start();


        back = (Button) findViewById(R.id.bback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            textView.setText(" "+event.values[0]);
            double px =event.values[0];
            if(px <= 0.5){
                mediaPlayer.pause();
            }
            else {
                if(mediaPlayer.isPlaying()){

                }
                else{
                    mediaPlayer.start();
                }
            }
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }



    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProxi, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
