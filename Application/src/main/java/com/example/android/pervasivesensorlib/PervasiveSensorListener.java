package com.example.android.pervasivesensorlib;

public interface PervasiveSensorListener {

    void OnPervasiveSensorChanged(final float[] sensorValues, final int sensorType);
}
