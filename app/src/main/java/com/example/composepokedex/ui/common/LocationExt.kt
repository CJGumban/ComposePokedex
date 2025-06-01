package ph.theorangeplatform.camtime.ui.common

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.core.content.ContextCompat
import com.example.composepokedex.data.handlers.LogHandler


/*
@SuppressLint("MissingPermission")
suspend fun FusedLocationProviderClient.getLocationAwait(): Location? = suspendCancellableCoroutine { continuation ->
    getCurrentLocation(PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
        override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
        override fun isCancellationRequested() = false
    }).addOnSuccessListener { location: Location? ->
        continuation.resume(location)
        LogHandler.d("FusedLocation: $location")
    }.addOnFailureListener { exception ->
        continuation.resume(null)
        LogHandler.e("FusedLocation: ${exception.message}")
    }
}
*/

/*
suspend fun Geocoder.getCityAwait(location: LocationCity): String = suspendCancellableCoroutine { continuation ->

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getFromLocation(
            location.lat,
            location.lng,
            1,
            object : Geocoder.GeocodeListener {
                override fun onGeocode(addresses: MutableList<Address>) {
                    if (addresses.isNotEmpty()) {
                        LogHandler.d("Geocoder: $addresses")
                        addresses[0].apply {
                            if(featureName != null && thoroughfare != null) {
                                continuation.resume("$featureName $thoroughfare")
                            } else if(locality != null) {
                                continuation.resume(locality)
                            } else if(subAdminArea != null) {
                                continuation.resume(subAdminArea)
                            } else if(adminArea != null) {
                                continuation.resume(adminArea)
                            } else {
                                continuation.resume("Unknown Location.")
                            }
                        }
                    } else {
                        continuation.resume("Unknown Location.")
                        LogHandler.d("Geocoder: No addresses found")
                    }
                }

                override fun onError(errorMessage: String?) {
                    super.onError(errorMessage)
                    continuation.resume("Unknown Location.")
                    LogHandler.e("Geocoder: $errorMessage")
                }

            })
    } else {
        val addresses = getFromLocation(location.lat, location.lng, 1)
        if (addresses?.isNotEmpty() == true) {
            LogHandler.d("Geocoder: $addresses")
            addresses[0].apply {
                if(featureName != null && thoroughfare != null) {
                    continuation.resume("$featureName $thoroughfare")
                } else if(locality != null) {
                    continuation.resume(locality)
                } else if(subAdminArea != null) {
                    continuation.resume(subAdminArea)
                } else if(adminArea != null) {
                    continuation.resume(adminArea)
                } else {
                    continuation.resume("Unknown Location.")
                }
            }
        } else {
            continuation.resume("Unknown Location.")
            LogHandler.d("Geocoder: No addresses found")
        }
    }

}
*/


fun isLocationEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}

fun canGetLocation(context: Context): Boolean {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    return permissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }
}

@Composable
fun LocationStatusReceiver(
    context: Context,
    onLocationChanged: () -> Unit,
){
    DisposableEffect(key1 = Unit) {
        val locationReceiver = object : BroadcastReceiver() {
            override fun onReceive(c: Context?, i: Intent?) {
                if (i?.action == LocationManager.PROVIDERS_CHANGED_ACTION) {
                    LogHandler.d("LocationReceiver onReceive: ${i.action}")
                    onLocationChanged()
                }
            }
        }
        context.registerReceiver(
            locationReceiver,
            IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        )
        onDispose {
            context.unregisterReceiver(locationReceiver)
        }
    }
}








