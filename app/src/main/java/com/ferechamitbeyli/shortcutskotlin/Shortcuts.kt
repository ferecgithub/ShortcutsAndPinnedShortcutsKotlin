package com.ferechamitbeyli.shortcutskotlin

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService

const val shortcut_website_id = "id_website"
const val shortcut_messages_id = "id_messages"

object Shortcuts {

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun setup(context: Context) {
        val shortcutManager = getSystemService(context, ShortcutManager::class.java)

        val shortcutWebsite = ShortcutInfo.Builder(context, shortcut_website_id)
            .setShortLabel("Website")
            .setLongLabel("Open the website!")
            .setIcon(Icon.createWithResource(context, R.drawable.ic_public))
            .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ferecgithub")))
            .build()

        val intents = arrayOf(Intent(Intent.ACTION_VIEW, null, context, MainActivity::class.java),
            Intent(Intent.ACTION_VIEW, null, context, Messages::class.java))

        val shortcutMessages = ShortcutInfo.Builder(context, shortcut_messages_id)
            .setShortLabel("Messages")
            .setLongLabel("Send a message!")
            .setIcon(Icon.createWithResource(context, R.drawable.ic_message))
            .setIntents(intents)
            .build()

        shortcutManager!!.dynamicShortcuts = listOf(shortcutWebsite, shortcutMessages)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setupPins(context: Context, shortcut_id: String, requestCode: Int) {

        val shortcutManager = getSystemService(context, ShortcutManager::class.java)

        if (shortcutManager!!.isRequestPinShortcutSupported) {
            val pinShortcutInfo =
                ShortcutInfo.Builder(context, shortcut_id).build()

            val pinnedShortcutCallbackIntent =
                shortcutManager.createShortcutResultIntent(pinShortcutInfo)

            val successCallback = PendingIntent.getBroadcast(
                context, requestCode,
                pinnedShortcutCallbackIntent,0
            )

            shortcutManager.requestPinShortcut(
                pinShortcutInfo,
                successCallback.intentSender
            )
        }
    }

}