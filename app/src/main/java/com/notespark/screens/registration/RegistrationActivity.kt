package com.notespark.screens.registration

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
import com.notespark.screens.main.MainActivity
import com.notespark.screens.registration.di.DaggerRegistrationComponent
import com.notespark.screens.registration.di.RegistrationModule
import kotlinx.android.synthetic.main.activity_login.emailInput
import kotlinx.android.synthetic.main.activity_login.passwordInput
import kotlinx.android.synthetic.main.activity_login.progressBar
import kotlinx.android.synthetic.main.activity_registration.*
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

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, RegistrationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
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

    override fun showInvalidEmailError() {
        emailInput.error = getString(R.string.invalid_email)
    }

    override fun showInvalidPasswordError() {
        passwordInput.error = getString(R.string.invalid_password)
    }

    override fun isDateValid(valid: Boolean) {
        createAccountBtn.isEnabled = valid
    }

    override fun startApp(currentUser: FirebaseUser?){
        if (currentUser != null && !currentUser.isAnonymous) {
            Toast.makeText(applicationContext, getString(R.string.welcome), Toast.LENGTH_LONG).show()
            MainActivity.launch(this)
            finish()
        } else {
            Toast.makeText(this, getString(R.string.authentication_failed),
                Toast.LENGTH_SHORT).show()
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
        createAccountBtn.setOnClickListener { presenter.onSignUp(emailInput.trimmedString(), passwordInput.trimmedString()) }
    }
}