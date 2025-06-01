package ph.theorangeplatform.camtime.ui.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class NotificationReceiver(
    private var callback: () -> Unit
): BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        callback.invoke()
    }

    fun register(context: Context){
        LocalBroadcastManager.getInstance(context)
            .registerReceiver(this, IntentFilter(FCM_RECEIVER))
    }

    fun unregister(context: Context){
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this)
    }

    companion object {
        const val FCM_RECEIVER = "fcm_receiver"
        fun broadcast(context: Context){
            val intent = Intent(FCM_RECEIVER)
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }
    }

}