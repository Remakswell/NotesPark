package com.notespark.screens.login

import com.notespark.common.arch.View

interface LoginView : View {
    fun showProgress()
    fun hideProgress()
    fun clearErrors()
    fun showEmptyEmailError()
    fun showInvalidEmailError()
    fun showEmptyPasswordError()
    fun showInvalidPasswordError()
}