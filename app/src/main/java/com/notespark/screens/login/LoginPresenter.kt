package com.notespark.screens.login

import com.notespark.common.Util.ValidatorUtil.isEmailValid
import com.notespark.common.Util.ValidatorUtil.isPasswordValid
import com.notespark.common.arch.Presenter

class LoginPresenter : Presenter<LoginView>() {

    fun loginUser(email: String, password: String){
        view?.clearErrors()
        view?.showProgress()
        when {
            email.isBlank() -> view?.showEmptyEmailError()
            !isEmailValid(email) -> view?.showInvalidEmailError()
            password.isBlank() -> view?.showEmptyPasswordError()
            !isPasswordValid(password) -> view?.showInvalidPasswordError()
            else -> {
                //
            }
        }
    }
}