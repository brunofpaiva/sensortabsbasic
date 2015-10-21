package com.example.android.common.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.common.view.SlidingTabLayout;
import com.example.android.slidingtabsbasic.R;

/**
 * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
 * The individual pages are simple and just display two lines of text. The important section of
 * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
 * {@link SlidingTabLayout}.
 */
public class SamplePagerAdapter extends PagerAdapter {

    private static final int NUMBER_OF_SENSOR_TABS = 13;

    private Activity mActivity;

    public SamplePagerAdapter(final Activity activity) {
        mActivity = activity;
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

        // Retrieve a TextView from the inflated View, and update it's text
        final TextView title = (TextView) view.findViewById(R.id.item_title);
        final Button sendEmailBt = (Button) view.findViewById(R.id.bt_sent);

//      TODO
//      PervasiveSensor sensor = whichSensorIs();
        final String sensor = wichSensorIs(position);

//      TODO
//      title.setText(sensor.getName());
        title.setText(sensor);
        sendEmailBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //TODO
//                sendEmail(sensor.getCurValue());
                sendEmail("Sensor data goes here");
            }
        });

        return view;
    }

    /**
     * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
     * {@link View}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private String wichSensorIs(final int position) {
        String sensorName = "No name";
        switch (position) {
            case 0:
                sensorName = "TYPE_ACCELEROMETER";
                break;
            case 1:
                sensorName = "TYPE_AMBIENT_TEMPERATURE";
                break;
            case 2:
                sensorName = "TYPE_GRAVITY";
                break;
            case 3:
                sensorName = "TYPE_GYROSCOPE";
                break;
            case 4:
                sensorName = "TYPE_LIGHT";
                break;
            case 5:
                sensorName = "TYPE_LINEAR_ACCELERATION";
                break;
            case 6:
                sensorName = "TYPE_MAGNETIC_FIELD";
                break;
            case 7:
                sensorName = "TYPE_ORIENTATION";
                break;
            case 8:
                sensorName = "TYPE_PRESSURE";
                break;
            case 9:
                sensorName = "TYPE_PROXIMITY";
                break;
            case 10:
                sensorName = "TYPE_RELATIVE_HUMIDITY";
                break;
            case 11:
                sensorName = "TYPE_ROTATION_VECTOR";
                break;
            case 12:
                sensorName = "TYPE_TEMPERATURE";
                break;
        }
        return sensorName;
    }

    private void sendEmail(String sensorData) {
        Log.i("Send email", "");
        String[] TO = {"brunofpaiva@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Testing Sensor Application");
        emailIntent.putExtra(Intent.EXTRA_TEXT, sensorData);

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