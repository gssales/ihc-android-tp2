package br.ufrgs.gssales.ihc_tp2_p1e3

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.textservice.TextServicesManager
import br.ufrgs.gssales.ihc_tp2_p1e3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometer != null)
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
        else
            binding.txtMessage.text = "Seu dispositivo não suporta acelerometro"
    }

    override fun onPause() {
        super.onPause()
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(e: SensorEvent?) {
        e ?: return
        val sensor = e.sensor
        if (sensor.type == Sensor.TYPE_ACCELEROMETER) {
            binding.edtX.setText(e.values[0].toString())
            binding.edtY.setText(e.values[1].toString())
            binding.edtZ.setText(e.values[2].toString())

            if (e.values[2] >= 9.8) {
                val i = Intent(this, ResultActivity::class.java)
                startActivity(i)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }
}