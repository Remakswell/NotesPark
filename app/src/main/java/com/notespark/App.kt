package com.notespark

import android.app.Application
import com.notespark.common.di.AppComponent
import com.notespark.common.di.DaggerAppComponent

class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}