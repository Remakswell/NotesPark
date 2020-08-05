package com.notespark.screens.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.notespark.App
import com.notespark.R
import com.notespark.screens.add.di.AddModule
import com.notespark.screens.add.di.DaggerAddComponent
import kotlinx.android.synthetic.main.activity_add.*
import javax.inject.Inject

class AddActivity : AppCompatActivity(), AddView {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, AddActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var presenter: AddPresenter

    val component by lazy {
        DaggerAddComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(AddModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        component.inject(this)
        presenter.bindView(this)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }

    private fun initView(){
        addToolbar.setNavigationIcon(R.drawable.ic_back)
        addToolbar.setNavigationOnClickListener { finish() }
        addToolbar.title = "Add"

        editDescription.requestFocus()
    }
}