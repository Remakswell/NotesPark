package com.notespark.screens.add.di

import com.notespark.common.di.ActivityScope
import com.notespark.common.di.AppComponent
import com.notespark.screens.add.AddActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [AddModule::class], dependencies = [AppComponent::class])
interface AddComponent {
    fun inject(target: AddActivity)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun activity(activity: AddActivity): Builder

        fun appComponent(component: AppComponent): Builder

        fun plus(module: AddModule): Builder

        fun build(): AddComponent
    }
}