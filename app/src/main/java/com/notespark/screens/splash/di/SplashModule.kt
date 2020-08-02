package com.notespark.screens.splash.di

import com.notespark.common.di.ActivityScope
import com.notespark.screens.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SplashModule {
    @Provides
    @ActivityScope
    fun presenter() = SplashPresenter()
}