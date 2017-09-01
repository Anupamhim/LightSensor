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

public class LightActivity extends Activity implements SensorEventListener {

    Button back;
    SensorManager mSensorManager;
    Sensor mLight;
    TextView textView;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        textView =(TextView)findViewById(R.id.textView);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
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

        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            textView.setText(" "+event.values[0]);
            double l =event.values[0];
            if(l <= 20.0){
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
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
