package com.notespark.screens.registration

import com.google.firebase.auth.FirebaseAuth
import com.notespark.common.arch.Presenter
import com.notespark.common.util.ValidatorUtil

class RegistrationPresenter(private val auth: FirebaseAuth) : Presenter<RegistrationView>() {

    fun onSignUp(email: String, password: String) {
        view?.showProgress()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { createTask ->
                if (createTask.isSuccessful) {
                    val currentUser = createTask.result?.user
                    view?.hideProgress()
                    view?.startApp(currentUser)
                } else {
                    view?.hideProgress()
                    view?.startApp(null)
                }
            }
    }

    fun loginDataChanged(email: String, password: String){
        if (!ValidatorUtil.isEmailValid(email)){
            view?.showInvalidEmailError()
            view?.isDateValid(false)
        } else if (!ValidatorUtil.isPasswordValid(password)){
            view?.showInvalidPasswordError()
            view?.isDateValid(false)
        } else {
            view?.isDateValid(true)
        }
    }
}