package com.notespark.screens.login.di

import com.notespark.common.di.ActivityScope
import com.notespark.screens.login.LoginPresenter

import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    @ActivityScope
    fun presenter() = LoginPresenter()
}