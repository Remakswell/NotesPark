package com.notespark.common.di

import com.google.firebase.auth.FirebaseAuth
import com.notespark.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: App)

    fun app(): App

    fun auth(): FirebaseAuth

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(app: App): Builder

        fun plus(module: AppModule): Builder

        fun build(): AppComponent
    }
}