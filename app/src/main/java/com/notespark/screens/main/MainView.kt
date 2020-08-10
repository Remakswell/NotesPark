package com.notespark.screens.main

import com.notespark.common.arch.View

interface MainView : View {
    fun openLoginActivity()
    fun createNotes()
    fun changeNotes(title: String, notes: String)
}