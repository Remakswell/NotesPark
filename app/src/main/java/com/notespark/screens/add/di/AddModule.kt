package com.notespark.screens.add.di

import com.notespark.common.di.ActivityScope
import com.notespark.screens.add.AddPresenter

import dagger.Module
import dagger.Provides

@Module
class AddModule {
    @Provides
    @ActivityScope
    fun presenter() = AddPresenter()
}