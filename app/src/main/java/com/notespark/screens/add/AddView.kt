package com.notespark.screens.add

import com.notespark.common.arch.View

interface AddView : View {
    fun showEmptyDataToast()
    fun sendResult(title: String, notes: String)
}