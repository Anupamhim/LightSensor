package com.example.anupam.lightsensor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends Activity  implements View.OnClickListener{

    Button start, stop,proxi,light;


    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Intent intent =new Intent(getBaseContext(),MyService.class);

        start = (Button) findViewById(R.id.bstart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);

            }
        });

        stop = (Button) findViewById(R.id.bstop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);

            }
        });

        proxi = (Button) findViewById(R.id.bproxi);
        proxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProxiMeter.class);
                startActivity(intent);
                finish();

            }
        });

        light = (Button) findViewById(R.id.blight);
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LightActivity.class);
                startActivity(intent);
                finish();


            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}
