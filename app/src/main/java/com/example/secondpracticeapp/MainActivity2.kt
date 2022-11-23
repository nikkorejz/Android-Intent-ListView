package com.example.secondpracticeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun onGoodMood(v: View) {
        val intent = Intent()
        intent.putExtra("RealCode", 555)
        setResult(777, intent)
        finish()
    }

    fun onBadMood(v: View) {
        setResult(RESULT_CANCELED)
        finish()
    }
}