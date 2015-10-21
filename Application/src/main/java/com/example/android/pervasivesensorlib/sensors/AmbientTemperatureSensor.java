package com.example.android.pervasivesensorlib.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import com.example.android.pervasivesensorlib.PervasiveSensor;

public class AmbientTemperatureSensor extends PervasiveSensor {

    @Override
    public String readSensor() {
        return null;
    }

    @Override
    public String getSensorName() {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
