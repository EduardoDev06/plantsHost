package com.bootcampnttdata6.plantshost.features.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bootcampnttdata6.plantshost.R

class AuthActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            SplashScreen.KeepOnScreenCondition { viewModel.isLoading.value }
        }
        setContentView(R.layout.activity_auth)
    }
}