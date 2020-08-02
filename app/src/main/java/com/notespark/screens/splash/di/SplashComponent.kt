package com.notespark.screens.splash.di

import com.notespark.common.di.ActivityScope
import com.notespark.common.di.AppComponent
import com.notespark.screens.splash.SplashActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [SplashModule::class], dependencies = [AppComponent::class])
interface SplashComponent {
    fun inject(target: SplashActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: SplashActivity): Builder

        fun appComponent(component: AppComponent): Builder

        fun plus(module: SplashModule): Builder

        fun build(): SplashComponent
    }
}