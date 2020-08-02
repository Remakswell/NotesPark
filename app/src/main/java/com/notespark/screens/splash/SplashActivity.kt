package com.notespark.screens.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.notespark.App
import com.notespark.R
import com.notespark.screens.main.MainActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashView {

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.bindView(this)
        presenter.startTimer()
    }

    override fun openMain() {
        MainActivity.launch(this)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}