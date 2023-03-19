package com.example.activitystatestest

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
	@Suppress("DEPRECATION")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		findViewById<Button>(R.id.show).setOnClickListener {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
				window?.insetsController?.let {
					window.setDecorFitsSystemWindows(true)
					window.insetsController?.show(WindowInsets.Type.systemBars())
				}
			} else {
				window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
				window.decorView.systemUiVisibility = 0
			}
		}

		findViewById<Button>(R.id.hide).setOnClickListener {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
				window?.insetsController?.let {
					window.setDecorFitsSystemWindows(false)
					it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
					it.hide(WindowInsets.Type.systemBars())
				}
			} else {
				window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
				window.decorView.systemUiVisibility =
					(View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
			}
		}
	}
}