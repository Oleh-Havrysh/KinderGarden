package ua.nure.havrysh.iot

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.progressBarOverall
import kotlinx.android.synthetic.main.activity_main.progressX
import kotlinx.android.synthetic.main.activity_main.progressY
import kotlinx.android.synthetic.main.activity_main.progressZ
import kotlinx.android.synthetic.main.activity_main.text_summary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SensorEventListener {
    
    private var mSensorManager: SensorManager? = null
    private var mAccSensor: Sensor? = null
    
    private var movementActivity = 0f
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSensor()
        
        startSensorsSending()
    }
    
    private fun startSensorsSending() {
        val childId = PreferenceManager.getDefaultSharedPreferences(this).getString("id", "")
        
        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                try {
                    Rest.easyGo
                        .postSensorData(SensorsRequest(movementActivity.toInt(), Child(childId)))
                        .await()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(5000)
            }
        }
    }
    
    private fun initSensor() {
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccSensor = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }
    
    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }
    
    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mAccSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }
    
    private fun refreshMovementActivity(overall: Float) {
        movementActivity += overall - 0.16f
        if (movementActivity < 0)
            movementActivity = 0f
        
        text_summary.text = String.format("Activity: %d", movementActivity.toInt())
    }
    
    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        var overall = (Math.abs(Math.sqrt((x * x + y * y + z * z).toDouble()) - 9.81)).toFloat()
        progressX.progress = normalize(x)
        progressY.progress = normalize(y)
        progressZ.progress = normalize(z)
        
        progressBarOverall.progress = overall.toInt()
        
        refreshMovementActivity(overall)
        
        Log.d("qaz", "overall: $overall")
    }
    
    private fun normalize(value: Float): Int {
        return (value / 30 * 100).toInt()
    }
    
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> startActivity(Intent(this, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
