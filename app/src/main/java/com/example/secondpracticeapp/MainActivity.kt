package com.example.secondpracticeapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "У вас плохое настроение", Toast.LENGTH_SHORT).show()
        } else {
            val received = result.data
            Log.w(TAG, received?.getIntExtra("RealCode", -1).toString())
            Toast.makeText(this, "У вас хорошее настроение", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(TAG, "onCreate")
        savedInstanceState?.run {
            getString("MyKey")?.let { Log.v(TAG, it) }
        }
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT)
            .show()
        val myButton: Button = findViewById(R.id.ButtonOne)
        myButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
//            startActivity(intent)
//            finish()
        }

        val buttonEmail = findViewById<Button>(R.id.ButtonEmail)
        buttonEmail.setOnClickListener {
//            val intent = Intent(Intent.ACTION_SENDTO)
//            val emailAddress = "test@test.ru"
//            var subject = "Обращение в поддержку ()"
//            subject = Uri.encode(subject)
//            intent.data = Uri.parse("mailto:$emailAddress?subject=$subject")

//            val intent = Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "123456789", null))

            val phoneNumber = "123456789"
            val sms = Uri.parse("smsto:$phoneNumber")
            val intent = Intent(Intent.ACTION_SENDTO, sms)
            intent.putExtra("sms_body", "Hi there!")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Не найден почтовый клиент", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.run {
            putString("MyKey", "MyValue")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.run {
            getString("MyKey")?.let {
                Log.i(TAG, it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume")
//        Log.d(TAG, "onResume")
//        Log.i(TAG, "onResume")
//        Log.w(TAG, "onResume")
//        Log.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
    }
}