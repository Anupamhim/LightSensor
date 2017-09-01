package com.example.anupam.lightsensor;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyService extends Service implements SensorEventListener {

    SensorManager mSensorManager;
    AudioManager audioManager;
    Sensor light_sensor,acceleter,proximetter;



    public void onCreate(){
      //  Toast.makeText(getApplicationContext(),"Started service ",Toast.LENGTH_LONG).show();

    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub
       // Toast.makeText(getApplicationContext(),"ekhane keno ashce",Toast.LENGTH_LONG).show();
    }

    public void onSensorChanged(SensorEvent e) {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(),"onSensorChanged  ",Toast.LENGTH_LONG).show();

        if (e.sensor.getType() == Sensor.TYPE_LIGHT ) {

            if(e.values[0] < 30)
            {
                Toast.makeText(getApplicationContext(),"silent hook",Toast.LENGTH_SHORT).show();
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
            else
            {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
        }
        if (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                if(e.values[1] < -5)
                {
                    Toast.makeText(getApplicationContext(),"silent hook",Toast.LENGTH_SHORT).show();
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }
                else
                {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
             }
        if (e.sensor.getType() == Sensor.TYPE_PROXIMITY)
            {
                if(e.values[0] < 5)
                {
                    Toast.makeText(getApplicationContext(),"silent hook",Toast.LENGTH_SHORT).show();
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }
                else
                {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
            }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int Id)
    {

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        light_sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        acceleter = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximetter = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        mSensorManager.registerListener(this,light_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,acceleter,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,proximetter,SensorManager.SENSOR_DELAY_NORMAL);

        return START_STICKY;
    }
    public void onDestroy()
    {
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        mSensorManager.unregisterListener(this);

    }
}

