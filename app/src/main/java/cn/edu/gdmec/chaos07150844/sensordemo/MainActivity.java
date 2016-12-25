package cn.edu.gdmec.chaos07150844.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager searchManager;
    private Sensor jia,fang,guang;
    private TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1= (TextView) findViewById(R.id.jia);
        tv2= (TextView) findViewById(R.id.fang);
        tv3= (TextView) findViewById(R.id.guang);
        

        searchManager= (SensorManager) getSystemService(SENSOR_SERVICE);

        jia=searchManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        fang=searchManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        guang=searchManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        searchManager.registerListener(this,jia,SensorManager.SENSOR_DELAY_NORMAL);
        searchManager.registerListener(this,fang,SensorManager.SENSOR_DELAY_NORMAL);
        searchManager.registerListener(this,guang,SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        searchManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x= event.values[SensorManager.DATA_X];
        float y = event.values[SensorManager.DATA_Y];
        float z= event.values[SensorManager.DATA_Z];

        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            tv2.setText("方位："+x+","+y+","+z);

        }else if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            tv1.setText("加速度："+x+","+y+","+z);
        }else{
            tv3.setText("光线"+event.values[0]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}