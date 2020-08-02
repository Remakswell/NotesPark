package com.notespark.screens.login.di

import com.notespark.common.di.ActivityScope
import com.notespark.common.di.AppComponent
import com.notespark.screens.login.LoginActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [LoginModule::class], dependencies = [AppComponent::class])
interface LoginComponent {
    fun inject(target: LoginActivity)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun activity(activity: LoginActivity): Builder

        fun appComponent(component: AppComponent): Builder

        fun plus(module: LoginModule): Builder

        fun build(): LoginComponent
    }
}