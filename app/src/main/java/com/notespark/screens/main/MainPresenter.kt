package com.notespark.screens.main

import com.google.firebase.auth.FirebaseAuth
import com.notespark.common.arch.Presenter

class MainPresenter(private val auth: FirebaseAuth) : Presenter<MainView>() {

    fun onLogOutClick(){
        auth.signOut()
        view?.openLoginActivity()
    }

    fun onAddClick(){
        view?.createNotes()
    }

    fun onItemClick(title: String, notes: String){
        view?.changeNotes(title, notes)
    }
}