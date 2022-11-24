package com.bootcampnttdata6.plantshost.features.auth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bootcampnttdata6.plantshost.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

    }
}