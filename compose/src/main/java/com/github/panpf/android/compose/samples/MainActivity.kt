package com.github.panpf.android.compose.samples

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        WindowCompat.setDecorFitsSystemWindows(window, true)
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        setTranslucentStatus()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

//    private fun setTranslucentStatus() {
//        // 5.0以上系统状态栏透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val window: Window = window
//            window.clearFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            )
//            window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.setStatusBarColor(Color.TRANSPARENT)
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        }
//    }
}