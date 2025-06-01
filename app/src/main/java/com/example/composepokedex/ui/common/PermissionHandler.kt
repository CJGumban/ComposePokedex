package ph.theorangeplatform.camtime.ui.common

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.composepokedex.data.handlers.LogHandler


@Composable
fun PermissionHandler(
    onGranted: () -> Unit,
    onDenied: () -> Unit,
) {

    val context = LocalContext.current
    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            LogHandler.d("PermissionHandler: granted!")
            onGranted()
        } else {
            LogHandler.d("PermissionHandler: denied!")
            onDenied()
        }
    }

    LaunchedEffect(Unit) {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if(permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }){
            LogHandler.d("PermissionHandler: granted!")
            onGranted()
        } else {
            launcherMultiplePermissions.launch(permissions)
        }
    }

}

fun checkAllPermission(
    context: Context, launcher:
    ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>
): Boolean {
    val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    return if(permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }){
        LogHandler.d("PermissionHandler: granted!")
        true
    } else {
        launcher.launch(permissions)
        false
    }
}
