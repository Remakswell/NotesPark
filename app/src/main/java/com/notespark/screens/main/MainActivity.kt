package com.notespark.screens.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.notespark.App
import com.notespark.R
import com.notespark.screens.add.AddActivity
import com.notespark.screens.login.LoginActivity
import com.notespark.screens.main.di.DaggerMainComponent
import com.notespark.screens.main.di.MainModule
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var presenter: MainPresenter

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

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            presenter.onAddClick()
        }
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

    override fun openAddActivity() {
        AddActivity.launch(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }

    private fun logoutDialog() {
        AlertDialog.Builder(this)
            .setMessage(R.string.logout_message)
            .setPositiveButton(android.R.string.ok) { _, _ -> presenter.onLogOutClick() }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .show()
    }
}