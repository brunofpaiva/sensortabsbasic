package com.example.android.common.adapter;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.common.view.SlidingTabLayout;
import com.example.android.pervasivesensorlib.PervasiveSensorListener;
import com.example.android.pervasivesensorlib.PervasiveSensorManager;
import com.example.android.slidingtabsbasic.R;

/**
 * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
 * The individual pages are simple and just display two lines of text. The important section of
 * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
 * {@link SlidingTabLayout}.
 */
public class SamplePagerAdapter extends PagerAdapter {

    private PervasiveSensorManager psManager;
    private static final int NUMBER_OF_SENSOR_TABS = 13;
    private String curSensorValue = "Sensor Value";
    private Activity mActivity;

    public SamplePagerAdapter(final Activity activity) {
        mActivity = activity;
        psManager = new PervasiveSensorManager(mActivity.getBaseContext());
    }

    /**
     * @return the number of pages to display
     */
    @Override
    public int getCount() {
        return NUMBER_OF_SENSOR_TABS;
    }

    /**
     * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
     * same object as the {@link View} added to the {@link ViewPager}.
     */
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    /**
     * Return the title of the item at {@code position}. This is important as what this method
     * returns is what is displayed in the {@link SlidingTabLayout}.
     * <p>
     * Here we construct one using the position value, but for real application the title should
     * refer to the item's contents.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "Sensor " + (position + 1);
    }

    /**
     * Instantiate the {@link View} which should be displayed at {@code position}. Here we
     * inflate a layout from the apps resources and then change the text view to signify the position.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        View view = mActivity.getLayoutInflater().inflate(R.layout.pager_item, container, false);
        // Add the newly created View to the ViewPager
        container.addView(view);

        final TextView sensorName = (TextView) view.findViewById(R.id.sensor_name);
        final TextView sensorValue = (TextView) view.findViewById(R.id.sensor_value);
        final ImageView sensorIcon = (ImageView) view.findViewById(R.id.sensor_icon);
        final Button sendEmailBt = (Button) view.findViewById(R.id.bt_sent);

        sensorName.setText(wichSensorIs(position, sensorIcon));
        sendEmailBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                sendEmail(sensorValue.getText().toString());
            }
        });

        PervasiveSensorListener psListener = sensorsListener(sensorValue, position);
        psManager.registerSensor(psListener, Sensor.TYPE_ACCELEROMETER, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_AMBIENT_TEMPERATURE, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_GRAVITY, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_GYROSCOPE, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_LIGHT, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_LINEAR_ACCELERATION, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_MAGNETIC_FIELD, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_ORIENTATION, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_PRESSURE, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_PROXIMITY, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_RELATIVE_HUMIDITY, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_ROTATION_VECTOR, SensorManager.SENSOR_DELAY_NORMAL);
        psManager.registerSensor(psListener, Sensor.TYPE_TEMPERATURE, SensorManager.SENSOR_DELAY_NORMAL);

        return view;
    }

    private PervasiveSensorListener sensorsListener(final TextView textView, final int sensor) {
        return (new PervasiveSensorListener() {
            @Override
            public void OnPervasiveSensorChanged(float[] sensorValues, int sensorType) {
                if (sensorType == Sensor.TYPE_ACCELEROMETER && sensor == 0) {
                    final float x = sensorValues[0];
                    final float y = sensorValues[1];
                    final float z = sensorValues[2];

                    curSensorValue = "X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z;
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_AMBIENT_TEMPERATURE && sensor == 1) {
                    final float temp = sensorValues[0];

                    curSensorValue = "Ambient Temperature Sensor: " + temp;
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_GRAVITY && sensor == 2) {
                    final float x = sensorValues[0];
                    final float y = sensorValues[1];
                    final float z = sensorValues[2];

                    curSensorValue = "Force of Gravity in X: " + x + "\n" +
                            "Force of Gravity in Y: " + y + "\n" +
                            "Force of Gravity in Z: " + z;
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_GYROSCOPE && sensor == 3) {
                    final float x = sensorValues[0];
                    final float y = sensorValues[1];
                    final float z = sensorValues[2];

                    curSensorValue = "Gyroscope X: " + x + "\n" +
                            "Gyroscope Y: " + y + "\n" +
                            "Gyroscope Z: " + z;
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_LIGHT && sensor == 4) {
                    float sensorValue = sensorValues[0];

                    curSensorValue = "Light Sensor: " + sensorValue + " SI lux units";
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_LINEAR_ACCELERATION && sensor == 5) {
                    final float x = sensorValues[0];
                    final float y = sensorValues[1];
                    final float z = sensorValues[2];

                    curSensorValue = "Linear Acceleration on X: " + x + "\n" +
                            "Linear Acceleration on Y: " + y + "\n" +
                            "Linear Acceleration on Z: " + z;
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD && sensor == 6) {
                    final float sensorValue = sensorValues[0];

                    curSensorValue = "Magnetic Field Sensor: " + sensorValue;
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_ORIENTATION && sensor == 7) {
                    final float angle = sensorValues[0];

                    curSensorValue = "Orientation Sensor: " + angle;
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_PRESSURE && sensor == 8) {
                    final float sensorValue = sensorValues[0];

                    curSensorValue = "Pressure Sensor: " + sensorValue + " hPa (millibars)";
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_PROXIMITY && sensor == 9) {
                    if (sensorValues[0] == 0) {
                        curSensorValue = "Proximity Sensor: Near";
                    } else {
                        curSensorValue = "Proximity Sensor Far";
                    }
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_RELATIVE_HUMIDITY && sensor == 10) {
                    final float humidity = sensorValues[0];

                    curSensorValue = "Relative Humidity Sensor: " + humidity;
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_ROTATION_VECTOR && sensor == 11) {
                    final float x = sensorValues[0];
                    final float y = sensorValues[1];
                    final float z = sensorValues[2];

                    curSensorValue = "Rotation Vector X: " + x + "\n" +
                            "Rotation Vector Y: " + y + "\n" +
                            "Rotation Vector Z: " + z;
                    textView.setText(curSensorValue);
                }
                else if (sensorType == Sensor.TYPE_TEMPERATURE && sensor == 12) {
                    final float temp = sensorValues[0];

                    curSensorValue = "Temperature of Phone is: " + temp;
                    textView.setText(curSensorValue);
                }
            }
        });
    }

    public void unregisterSensors() {
        psManager.unregisterAllSensors();
    }

    /**
     * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
     * {@link View}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private String wichSensorIs(final int position, final ImageView icon) {
        String sensorName = "No name";
        switch (position) {
            case 0:
                sensorName = "ACCELEROMETER SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.accelerometer_sensor_icon));
                break;
            case 1:
                sensorName = "AMBIENT TEMPERATURE SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.ambtemperature_sensor_icon));
                break;
            case 2:
                sensorName = "GRAVITY SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.gravity_sensor_icon));
                break;
            case 3:
                sensorName = "GYROSCOPE SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.gyroscope_sensor_icon));
                break;
            case 4:
                sensorName = "LIGHT SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.light_sensor_icon));
                break;
            case 5:
                sensorName = "LINEAR ACCELERATION SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.linear_acceleration_sensor_icon));
                break;
            case 6:
                sensorName = "MAGNETIC FIELD SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.magnetic_field_sensor_icon));
                break;
            case 7:
                sensorName = "ORIENTATION SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.orientation_sensor_field_icon));
                break;
            case 8:
                sensorName = "PRESSURE SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.pressure_sensor_icon));
                break;
            case 9:
                sensorName = "PROXIMITY SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.proximity_sensor_icon));
                break;
            case 10:
                sensorName = "RELATIVE HUMIDITY SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.relative_humidity_sensor_icon));
                break;
            case 11:
                sensorName = "ROTATION VECTOR SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.rotation_vector_sensor_icon));
                break;
            case 12:
                sensorName = "TEMPERATURE SENSOR";
                icon.setImageDrawable(ContextCompat.getDrawable(mActivity.getBaseContext(), R.drawable.temperature_sensor_icon));
                break;
        }
        return sensorName;
    }

    private void sendEmail(final String sensorValue) {
        Log.i("Send email", "");
        String[] TO = {"brunofpaiva@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Testing Sensor Application");
        emailIntent.putExtra(Intent.EXTRA_TEXT, sensorValue);

        try {
            mActivity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.d("EMAIL", "Finished sending email...");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mActivity.getBaseContext(),
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}