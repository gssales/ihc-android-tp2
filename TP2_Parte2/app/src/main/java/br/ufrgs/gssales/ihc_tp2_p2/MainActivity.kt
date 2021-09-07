package br.ufrgs.gssales.ihc_tp2_p2

import android.Manifest
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.ufrgs.gssales.ihc_tp2_p2.databinding.ActivityMainBinding


const val PERMISSION_REQUEST_CODE = 1
class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_CODE
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometer != null)
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
        else
            binding.txtAccelerometerAlert.text = "Accelerometer not available"

        val distanceSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY, true)
        if (distanceSensor != null)
            sensorManager.registerListener(this, distanceSensor, SensorManager.SENSOR_DELAY_UI)
        else
            binding.txtDistanceAlert.text = "Proximity sensor not available"

        val magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        if (magneticSensor != null)
            sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_UI)
        else
            binding.txtMagneticFieldAlert.text = "Magnetic field sensor not available"

        val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (lightSensor != null)
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_UI)
        else
            binding.txtLightAlert.text = "Light sensor not available"

        binding.btnGps.setOnClickListener {
            val g = GPSTracker(applicationContext)
            val l = g.getLocation()
            if (l != null) {
                val lat = l.latitude
                val longi = l.longitude
                Toast.makeText(
                    applicationContext, "LAT: " + lat + "LONG: " +
                            longi, Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val serviceManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        serviceManager.unregisterListener(this)
    }

    override fun onSensorChanged(e: SensorEvent?) {
        e ?: return

        val sensor = e.sensor
        when (sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                binding.edtX.setText(java.lang.String.format("%.2f m/s²", e.values[0]))
                binding.edtY.setText(java.lang.String.format("%.2f m/s²", e.values[1]))
                binding.edtZ.setText(java.lang.String.format("%.2f m/s²", e.values[2]))
            }
            Sensor.TYPE_PROXIMITY -> binding.edtDistance.setText(
                java.lang.String.format(
                    "%.2f cm",
                    e.values[0]
                )
            )
            Sensor.TYPE_MAGNETIC_FIELD -> {
                binding.edtMagneticFieldX.setText(java.lang.String.format("%.2f μT", e.values[0]))
                binding.edtMagneticFieldY.setText(java.lang.String.format("%.2f μT", e.values[1]))
                binding.edtMagneticFieldZ.setText(java.lang.String.format("%.2f μT", e.values[2]))
            }
            Sensor.TYPE_LIGHT -> binding.edtLight.setText(
                java.lang.String.format(
                    "%.2f lx",
                    e.values[0]
                )
            )
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }
}