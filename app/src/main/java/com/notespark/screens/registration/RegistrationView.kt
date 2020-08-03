package com.notespark.screens.registration

import com.google.firebase.auth.FirebaseUser
import com.notespark.common.arch.View

interface RegistrationView : View {
    fun showProgress()
    fun hideProgress()
    fun showInvalidEmailError()
    fun showInvalidPasswordError()
    fun startApp(currentUser: FirebaseUser?)
    fun isDateValid(valid: Boolean)
}