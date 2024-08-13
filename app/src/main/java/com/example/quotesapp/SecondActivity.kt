package com.example.quotesapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val quote = intent.getStringExtra("quote")

        val textViewReceivedQuote = findViewById<TextView>(R.id.textViewReceivedQuote)
        textViewReceivedQuote.text = quote

        showNotification(quote!!)
    }

    private fun showNotification(quote: String) {
        val channelId = "quote_channel"
        val notificationId = 1

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification) // Make sure this resource exists
            .setContentTitle("Shared Quote")
            .setContentText(quote)
            .setStyle(NotificationCompat.BigTextStyle().bigText(quote)) // Enable expandable text
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 500, 1000, 500)) // Custom vibration pattern
            .setLights(0xFF00FF00.toInt(), 300, 1000) // Green light for notifications
            .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI) // Default sound
            .setPriority(NotificationCompat.PRIORITY_HIGH) // High priority

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Quote Notifications", NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = 0xFF00FF00.toInt() // Green light
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(0, 500, 1000, 500)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}