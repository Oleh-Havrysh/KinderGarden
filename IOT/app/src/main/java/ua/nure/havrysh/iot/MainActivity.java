package ua.nure.havrysh.iot;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final int REQUEST_PASS_GATES = 1;

    private SensorManager mSensorManager;
    private Sensor mAccSensor;

    @InjectView(R.id.progressX)
    ArcProgress pX;

    @InjectView(R.id.progressY)
    ArcProgress pY;

    @InjectView(R.id.progressZ)
    ArcProgress pZ;

    @InjectView(R.id.progressBarOverall)
    ProgressBar pO;

    @InjectView(R.id.text_summary)
    TextView summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initSensor();
    }

    private void initSensor() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private int normalize(float val) {
        return (int) (val / 30 * 100);
    }

    private float movementActivity = 0;

    private void refreshMovementActivity(float overall) {
        float d = overall - movementActivity;

        movementActivity += d * 0.001f * (d > 0 ? 1 : 0.5f);

        summary.setText(String.format("Activity: %d", (int) movementActivity));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0],
                y = event.values[1],
                z = event.values[2];
        float overall = (float) (Math.sqrt(x * x + y * y + z * z) - 9.81);
        pX.setProgress(normalize(x));
        pY.setProgress(normalize(y));
        pZ.setProgress(normalize(z));
        overall = normalize(overall);
        pO.setProgress((int) overall);

        refreshMovementActivity(overall);

        Log.d("qaz", "" + overall);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PASS_GATES) {
                movementActivity=0;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_pass_gates:
                startActivityForResult(PassActivity.prepareIntent(this, (int)movementActivity,
                        PreferenceManager.getDefaultSharedPreferences(this).getLong("id", 0)),
                        REQUEST_PASS_GATES);
                break;
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
