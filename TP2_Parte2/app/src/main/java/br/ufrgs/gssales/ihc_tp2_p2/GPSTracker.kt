package br.ufrgs.gssales.ihc_tp2_p2

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.core.content.ContextCompat

class GPSTracker(c: Context): LocationListener {
    private val context: Context = c

    fun getLocation(): Location? {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission for Location not granted", Toast.LENGTH_LONG).show()
            return null
        }
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10f, this)
            return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        } else {
            Toast.makeText(context, "Please enable GPS", Toast.LENGTH_LONG).show()
        }
        return null
    }

    override fun onLocationChanged(p0: Location) { }
}