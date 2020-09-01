package com.ferechamitbeyli.shortcutskotlin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 25) {
            Shortcuts.setup(applicationContext)
        }

        if (Build.VERSION.SDK_INT >= 28) {

            youtube_sc_btn.setOnClickListener {
                Shortcuts.setupPins(applicationContext, shortcut_website_id, 0)
            }

            messages_sc_btn.setOnClickListener {
                Shortcuts.setupPins(applicationContext, shortcut_messages_id, 1)
            }
        }

    }

}