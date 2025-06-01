/*
package ph.theorangeplatform.camtime.ui.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
import ph.theorangeplatform.camtime.MainActivity
import ph.theorangeplatform.camtime.R
import ph.theorangeplatform.camtime.data.handlers.LogHandler
import ph.theorangeplatform.camtime.data.handlers.PrefHandler
import ph.theorangeplatform.camtime.data.repos.INotificationRepo
import javax.inject.Inject


@AndroidEntryPoint
class NotificationService: FirebaseMessagingService() {

    @Inject
    lateinit var notificationRepo: INotificationRepo

    @Inject
    lateinit var prefHandler: PrefHandler

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            if(prefHandler.token?.isNotEmpty() == true){
                CoroutineScope(Dispatchers.IO).launch{
                    val bundle = Bundle()
                    val param = remoteMessage.data["param1"]?.split(".")

                    if(param != null && param.size >= 3){
                        val result = notificationRepo.getNotificationDetails(param[2], param[0]).singleOrNull()
                        if(result != null){
                            bundle.putString("refNo", result.refNo)
                            bundle.putString("screenId", param[0])
                            bundle.putString("userId", result.userId)
                            bundle.putString("userType", param[1])
                            bundle.putString("firstName", result.firstName ?: "")
                        }
                    }

                    sendNotification(remoteMessage, bundle)
                    NotificationReceiver.broadcast(this@NotificationService)

                }
            }
            LogHandler.d("NotificationService: ${it.title} ${remoteMessage.data}")
        }
    }

    override fun onNewToken(token: String) {
        LogHandler.d("NotificationService: new token $token")
        CoroutineScope(Dispatchers.IO).launch{
            notificationRepo.postNotificationToken(token)
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage, bundle: Bundle) {
      //  val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtras(bundle)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0 */
/* Request code *//*
, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "camtime_notification_id"

        val notifTitle = if (remoteMessage.notification?.title.isNullOrEmpty()) {
            getString(R.string.app_name)
        } else {
            remoteMessage.notification?.title ?: getString(R.string.app_name)
        }
        val notifBody = remoteMessage.notification?.body ?: ""
        val notificationId = System.currentTimeMillis().toInt()
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher_round)
        //    .setLargeIcon(bitmap)
            .setContentTitle(notifTitle)
            .setContentText(notifBody)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}*/
