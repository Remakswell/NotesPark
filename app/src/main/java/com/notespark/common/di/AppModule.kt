package com.notespark.common.di

import com.notespark.screens.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun presenter() = SplashPresenter()
}