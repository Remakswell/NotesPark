package com.notespark.screens.registration.di

import com.notespark.common.di.ActivityScope
import com.notespark.common.di.AppComponent
import com.notespark.screens.registration.RegistrationActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [RegistrationModule::class], dependencies = [AppComponent::class])
interface RegistrationComponent {
    fun inject(activity: RegistrationActivity)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun activity(activity: RegistrationActivity): Builder

        fun appComponent(component: AppComponent): Builder

        fun plus(module: RegistrationModule): Builder

        fun build(): RegistrationComponent

    }
}