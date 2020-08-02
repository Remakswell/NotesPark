package com.notespark.common.arch

abstract class Presenter<V : View> {
    protected var view: V? = null

    fun bindView(view: V) {
        this.view = view
    }

    fun unbindView() {
        this.view = null
    }
}