package com.notespark.screens.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.notespark.App
import com.notespark.R
import com.notespark.common.Util.trimmedString
import com.notespark.screens.login.di.DaggerLoginComponent
import com.notespark.screens.login.di.LoginModule
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {

    val component by lazy {
        DaggerLoginComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(LoginModule())
            .build()
    }

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        component.inject(this)
        presenter.bindView(this)

        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun clearErrors() {
        emailInput.error = null
        passwordInput.error = null
    }

    override fun showEmptyEmailError() {
        getString(R.string.empty_field)
    }

    override fun showInvalidEmailError() {
        getString(R.string.invalid_email)
    }

    override fun showEmptyPasswordError() {
        getString(R.string.empty_field)
    }

    override fun showInvalidPasswordError() {
        getString(R.string.invalid_password)
    }

    private fun initView(){
        signInBtn.setOnClickListener { presenter.loginUser(emailInput.trimmedString(), passwordInput.trimmedString()) }
    }
}