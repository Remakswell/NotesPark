package com.notespark.screens.login

import com.google.firebase.auth.FirebaseUser
import com.notespark.common.arch.View

interface LoginView : View {
    fun showProgress()
    fun hideProgress()
    fun showInvalidEmailError()
    fun showInvalidPasswordError()
    fun showLoginError()
    fun openRegistration()
    fun isDateValid(valid: Boolean)
    fun startApp(currentUser: FirebaseUser?)
}