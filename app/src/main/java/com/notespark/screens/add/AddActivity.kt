package com.notespark.screens.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

    override fun showEmptyDataToast() {
        Toast.makeText(this, getString(R.string.nothing_to_save), Toast.LENGTH_LONG).show()
    }

    override fun sendResult(title: String, notes: String) {
        val intent = Intent()
        intent.putExtra("title", title)
        intent.putExtra("notes", notes)
        setResult(Activity.RESULT_OK, intent)
    }

    private fun initView(){
        addToolbar.setNavigationIcon(R.drawable.ic_back)
        addToolbar.setNavigationOnClickListener { finish() }
        addToolbar.title = "Add"

        editNotes.requestFocus()

        saveBtn.setOnClickListener {
            presenter.onSaveBtnClick(editTitle.text.toString(), editNotes.text.toString())
            finish()
        }
    }
}