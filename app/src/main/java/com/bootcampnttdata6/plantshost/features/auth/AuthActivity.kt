package com.bootcampnttdata6.plantshost.features.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.features.main.MainActivity

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val btn = findViewById<TextView>(R.id.textView)
        btn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}