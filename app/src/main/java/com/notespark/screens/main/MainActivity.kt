package com.notespark.screens.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.notespark.App
import com.notespark.R
import com.notespark.screens.add.AddActivity
import com.notespark.screens.login.LoginActivity
import com.notespark.screens.main.data.model.ItemNotes
import com.notespark.screens.main.di.DaggerMainComponent
import com.notespark.screens.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        private const val REQUEST_CODE_CREATE_NOTES = 11
        private const val REQUEST_CODE_CHANGE_NOTES = 22
    }

    @Inject
    lateinit var presenter: MainPresenter
    private var notesList = mutableListOf<ItemNotes>()
    var itemPosition: Int = 0
    private var adapter: NotesAdapter? = null

    val component by lazy {
        DaggerMainComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(MainModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)
        setSupportActionBar(toolbar)
        presenter.bindView(this)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            logoutDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun openLoginActivity() {
        LoginActivity.launch(this)
        finish()
    }

    override fun createNotes() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_CREATE_NOTES)
    }

    override fun changeNotes(title: String, notes: String) {
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("notes", notes)
        startActivityForResult(intent, REQUEST_CODE_CHANGE_NOTES)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val title = data?.getStringExtra("title").toString()
        val notes = data?.getStringExtra("notes").toString()
        if (data == null) return

        when (requestCode) {
            REQUEST_CODE_CREATE_NOTES -> {
                notesList.add(ItemNotes(title, notes))
                adapter?.updateList(notesList)

            }
            REQUEST_CODE_CHANGE_NOTES -> {
                notesList[itemPosition] = ItemNotes(title, notes)
                adapter?.updateList(notesList)
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == 101) {
            notesList.removeAt(item.groupId)
            adapter?.updateList(notesList)
            true
        } else {
            super.onContextItemSelected(item)
        }
    }

    private fun logoutDialog() {
        AlertDialog.Builder(this)
            .setMessage(R.string.logout_message)
            .setPositiveButton(android.R.string.ok) { _, _ -> presenter.onLogOutClick() }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .show()
    }

    private fun initView() {
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            presenter.onAddClick()
        }
        initAdapter()
    }

    private fun initAdapter(){
        adapter = NotesAdapter(object : NotesAdapter.OnItemClickListener{
            override fun onItemClick(title: String, notes: String, position: Int) {
                itemPosition = position
                presenter.onItemClick(title, notes)
            }
        })
        val manager = GridLayoutManager(this, 2)
        recyclerNotesList.layoutManager = manager
        recyclerNotesList.adapter = adapter
    }
}