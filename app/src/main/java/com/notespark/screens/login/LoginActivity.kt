package com.notespark.screens.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.google.firebase.auth.FirebaseUser
import com.notespark.App
import com.notespark.R
import com.notespark.common.util.afterTextChanged
import com.notespark.common.util.trimmedString
import com.notespark.screens.login.di.DaggerLoginComponent
import com.notespark.screens.login.di.LoginModule
import com.notespark.screens.main.MainActivity
import com.notespark.screens.registration.RegistrationActivity
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

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
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

    override fun onStart() {
        super.onStart()
        presenter.doOnStart()
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

    override fun showInvalidEmailError() {
        emailInput.error = getString(R.string.invalid_email)
    }

    override fun showInvalidPasswordError() {
        passwordInput.error = getString(R.string.invalid_password)
    }

    override fun showLoginError() {
        Toast.makeText(applicationContext, getString(R.string.please_create_an_account), Toast.LENGTH_LONG).show()
    }

    override fun openRegistration(){
        RegistrationActivity.launch(this)
    }

    override fun isDateValid(valid: Boolean) {
        signInBtn.isEnabled = valid
    }

    override fun startApp(currentUser: FirebaseUser?) {
        if (currentUser != null && !currentUser.isAnonymous) {
            Toast.makeText(applicationContext, getString(R.string.welcome), Toast.LENGTH_LONG).show()
            MainActivity.launch(this)
            finish()
        }
    }

    private fun checkValidForm(){
        emailInput.afterTextChanged {
            presenter.loginDataChanged(emailInput.trimmedString(), passwordInput.trimmedString())
        }
        passwordInput.doAfterTextChanged {
            presenter.loginDataChanged(emailInput.trimmedString(), passwordInput.trimmedString())
        }
    }

    private fun initView(){
        checkValidForm()
        signInBtn.setOnClickListener { presenter.onLoginClick(emailInput.trimmedString(), passwordInput.trimmedString()) }
        createAccBtn.setOnClickListener { presenter.onRegistrationClick() }
    }
}