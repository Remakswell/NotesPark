package com.notespark

import android.app.Application
import com.notespark.common.di.AppComponent
import com.notespark.common.di.AppModule
import com.notespark.common.di.DaggerAppComponent

class App: Application() {
    val component by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .plus(AppModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}