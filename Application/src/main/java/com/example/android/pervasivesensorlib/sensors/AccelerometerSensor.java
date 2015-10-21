package com.example.android.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import com.example.android.pervasivesensorlib.PervasiveSensor;

public class AccelerometerSensor extends PervasiveSensor {

    private String mSensorValue = "Not initiated";
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;

    @Override
    public String readSensor() {
        return mSensorValue;
    }

    @Override
    public String getSensorName() {
        return "Accelerometer Sensor";
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        final Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            mSensorValue = "X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z;
            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                mSensorValue = "X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z;
                lastUpdate = curTime;

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
