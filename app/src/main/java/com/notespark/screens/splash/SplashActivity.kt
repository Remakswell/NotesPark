package com.notespark.screens.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.notespark.App
import com.notespark.R
import com.notespark.screens.login.LoginActivity
import com.notespark.screens.splash.di.DaggerSplashComponent
import com.notespark.screens.splash.di.SplashModule
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashView {

    val component by lazy {
        DaggerSplashComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(SplashModule())
            .build()
    }

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        component.inject(this)
        presenter.bindView(this)
        presenter.startTimer()
    }

    override fun openLogin() {
        LoginActivity.launch(this)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}