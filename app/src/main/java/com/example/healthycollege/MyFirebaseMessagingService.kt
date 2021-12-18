package com.example.healthycollege

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    private val TAG : String = this.javaClass.simpleName

    override fun onMessageReceived(remoteMessage: RemoteMessage){
       super.onMessageReceived(remoteMessage)
        Log.d("FCM",remoteMessage.notification?.title+"")
        Log.d("FCM",remoteMessage.notification?.body+"")
        sendNotification(remoteMessage.notification?.title, remoteMessage.notification?.body);

    }

    override fun onNewToken(token:String) {
        super.onNewToken(token)

    }

    private fun sendNotification(title:String?,body:String?){
        val uniId: Int = (System.currentTimeMillis() / 7).toInt()
        val channelId = "my_channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, title!!)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setSmallIcon(R.drawable.ic_email)
            .setGroup(title)

        val summarynotificationBuilder = NotificationCompat.Builder(this, title)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_email)
            .setGroup(title)
            .setGroupSummary(true)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 예외처리
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                title,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(uniId /* ID of notification */, notificationBuilder.build())
    }

}