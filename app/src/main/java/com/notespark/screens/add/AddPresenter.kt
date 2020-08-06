package com.notespark.screens.add

import com.notespark.common.arch.Presenter

class AddPresenter (): Presenter<AddView>() {

    fun onSaveBtnClick(title: String, notes: String){
        if (title.isBlank() && notes.isBlank()){
            view?.showEmptyDataToast()
        } else {
            view?.sendResult(title, notes)
        }
    }
}