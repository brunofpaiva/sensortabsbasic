package com.example.android.pervasivesensorlib;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;

public class PervasiveSensorManager implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private ArrayList<PervasiveSensorListener> myListeners;

    public PervasiveSensorManager(final Context context) {
        myListeners = new ArrayList<PervasiveSensorListener>();
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * Register a PervasiveSensorListener on PervasiveSensorManager to receive updates from this
     * sensor if the phone has.
     *
     * @param listener The PervasiveSensorListener that will listen the sensor.
     * @param sensorType Its a constant that identifies what sensor is that.
     *                   //Ex.: sensorType = Sensor.TYPE_ACCELEROMETER;
     * @param sensorUpdateTime Set the interval between sensor updates.
     *                   //Ex.: sensorUpdateTime = SensorManager.SENSOR_DELAY_NORMAL;
     * @return A boolean if the current device has or not the sensor in question.
     */
    public boolean registerSensor(final PervasiveSensorListener listener, final int sensorType,
                               final int sensorUpdateTime) {
        boolean hasSensor = false;
        mSensor = mSensorManager.getDefaultSensor(sensorType);
        if (mSensor != null) {
            mSensorManager.registerListener(this, mSensor, sensorUpdateTime);
            hasSensor = true;
        }
        myListeners.add(listener);
        return hasSensor;
    }

    public void unregisterSensor(final PervasiveSensorListener listener, final int sensorType) {
        final Sensor curSensor = mSensorManager.getDefaultSensor(sensorType);
        mSensorManager.unregisterListener(this, curSensor);
        myListeners.remove(listener);
    }

    public void unregisterAllSensors() {
        final Sensor allSensors = mSensorManager.getDefaultSensor(Sensor.TYPE_ALL);
        mSensorManager.unregisterListener(this, allSensors);
        myListeners.clear();
    }

    private void notifyPervasiveSensors(final float[] sensorValues, final int sensorType) {
        for (int i = 0; i < myListeners.size(); i++) {
            myListeners.get(i).OnPervasiveSensorChanged(sensorValues, sensorType);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        notifyPervasiveSensors(event.values, event.sensor.getType());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
