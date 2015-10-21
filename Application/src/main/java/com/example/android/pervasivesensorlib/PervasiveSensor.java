package com.example.android.pervasivesensorlib;

import android.hardware.SensorEventListener;

public abstract class PervasiveSensor implements SensorEventListener {

    public abstract String readSensor();

    public abstract String getSensorName();
}
