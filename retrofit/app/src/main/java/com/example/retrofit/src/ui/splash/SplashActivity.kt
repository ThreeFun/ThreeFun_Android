package com.example.retrofit.src.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.R
import com.example.retrofit.src.ui.sign.SignActivity

class SplashActivity : AppCompatActivity() {
    //자동 로그인 -> 성공하면 종료
    //네비게이션이 따로 없고
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(mainLooper)
        handler.postDelayed({
            //스플래시 후 작업
            startActivity(Intent(this, SignActivity::class.java))
        }, 1700)

        //자동로그인 로직은 일단 킵
    }
}