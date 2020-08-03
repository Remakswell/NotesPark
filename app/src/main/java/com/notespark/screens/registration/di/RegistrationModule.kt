package com.notespark.screens.registration.di

import com.notespark.common.di.ActivityScope
import com.notespark.screens.registration.RegistrationPresenter
import dagger.Module
import dagger.Provides

@Module
class RegistrationModule {
    @Provides
    @ActivityScope
    fun presenter() = RegistrationPresenter()
}