package com.notespark.screens.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.notespark.App
import com.notespark.R
import com.notespark.screens.registration.di.DaggerRegistrationComponent
import com.notespark.screens.registration.di.RegistrationModule
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity(), RegistrationView {

    @Inject
    lateinit var presenter: RegistrationPresenter

    val component by lazy {
        DaggerRegistrationComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(RegistrationModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        component.inject(this)
        presenter.bindView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}