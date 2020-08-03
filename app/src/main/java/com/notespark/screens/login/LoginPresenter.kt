package com.notespark.screens.login

import com.google.firebase.auth.FirebaseAuth
import com.notespark.common.util.ValidatorUtil.isEmailValid
import com.notespark.common.util.ValidatorUtil.isPasswordValid
import com.notespark.common.arch.Presenter

class LoginPresenter(private val auth: FirebaseAuth) : Presenter<LoginView>() {

    fun onLoginClick(email: String, password: String){
        view?.showProgress()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = task.result?.user
                    view?.hideProgress()
                    view?.startApp(currentUser)
                } else {
                    view?.hideProgress()
                    view?.showLoginError()
                    view?.startApp(null)
                }
            }
    }

    fun loginDataChanged(email: String, password: String){
        if (!isEmailValid(email)){
            view?.showInvalidEmailError()
            view?.isDateValid(false)
        } else if (!isPasswordValid(password)){
            view?.showInvalidPasswordError()
            view?.isDateValid(false)
        } else {
            view?.isDateValid(true)
        }
    }

    fun onRegistrationClick(){
        view?.openRegistration()
    }

    fun doOnStart(){
        val currentUser = auth.currentUser
        view?.startApp(currentUser)
    }
}