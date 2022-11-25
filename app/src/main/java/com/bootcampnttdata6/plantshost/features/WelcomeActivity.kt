package com.bootcampnttdata6.plantshost.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.features.auth.AuthActivity

class WelcomeActivity : AppCompatActivity() {

    private val viewModel: WelcomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.isLoading.value }
        }.also {
            startActivity(Intent(this@WelcomeActivity, AuthActivity::class.java))
            finish()
        }
        setContentView(R.layout.activity_welcome)
    }
}