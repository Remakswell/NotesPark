package com.notespark.screens.splash

import android.os.Handler
import com.notespark.common.arch.Presenter

class SplashPresenter : Presenter<SplashView>() {
    companion object {
        private const val SPLASH_DISPLAY_TIME = 1000L
    }

    fun startTimer() {
        Handler().postDelayed({
            view?.openLogin()
        }, SPLASH_DISPLAY_TIME)
    }
}