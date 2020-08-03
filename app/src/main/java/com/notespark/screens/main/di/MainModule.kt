package com.notespark.screens.main.di

import com.google.firebase.auth.FirebaseAuth
import com.notespark.common.di.ActivityScope

import com.notespark.screens.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    @ActivityScope
    fun presenter(auth: FirebaseAuth) = MainPresenter(auth)
}