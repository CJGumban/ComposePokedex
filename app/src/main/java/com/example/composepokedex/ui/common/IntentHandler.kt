package ph.theorangeplatform.camtime.ui.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import com.example.composepokedex.data.handlers.LogHandler

object IntentHandler {

    fun openAppSettings(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun openSecuritySettings(context: Context) {
        val intent = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> Intent(Settings.ACTION_BIOMETRIC_ENROLL)
            else -> Intent(Settings.ACTION_SECURITY_SETTINGS)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun openEnableLocation(context: Context) {
        try {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } catch (ex: Exception){
            LogHandler.e(ex)
        }
    }

/*
    fun openMaps(context: Context, long: String, lat: String) {
        try {
            val intentUri = Uri.parse(Constants.accessGoogleMaps(lat = lat, long = long))
            val intent = Intent(Intent.ACTION_VIEW, intentUri)
            intent.setPackage(Constants.GOOGLE_MAPS_PACKAGE)
            context.startActivity(intent)
        } catch (ex: Exception){
            Loghandler.e("$ex, Opening Google Playstore")
            val playstoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.GOOGLE_MAPS_LINK))
            context.startActivity(playstoreIntent)
        }
    }
*/

/*    fun openMapsHuawei(context: Context, long: String, lat: String) {
        try {
            val mapUrl = Uri.parse(Constants.accessHuaweiPetalMaps(lat = lat, long = long))
            val intent = Intent(Intent.ACTION_VIEW, mapUrl)
            intent.setPackage(Constants.HUAWEI_PETAL_MAPS_PACKAGE)
            context.startActivity(intent)
        } catch (ex: Exception){
            LogHandler.e("$ex, Opening Huawei App Gallery")
            val appGalleryIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.HUAWEI_PETAL_MAPS_LINK))
            context.startActivity(appGalleryIntent)
        }
    }*/
    
    fun openMapsWithLink(context: Context, locationUrl: String) {
        if (locationUrl.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))
            intent.setPackage("com.google.android.apps.maps")
            context.startActivity(intent)
        }
    }
}
