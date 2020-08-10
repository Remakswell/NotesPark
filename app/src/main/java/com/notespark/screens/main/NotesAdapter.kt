package com.notespark.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.notespark.R
import com.notespark.screens.main.data.model.ItemNotes

class NotesAdapter(private val itemClick: OnItemClickListener): RecyclerView.Adapter<NotesAdapter.ItemVH>() {

    private var notesList = mutableListOf<ItemNotes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false)
        return ItemVH(view)
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        val item = notesList[position]
        holder.title.text = item.title
        holder.notes.text = item.notes

        holder.emailContainer.setOnClickListener { itemClick.onItemClick(item.title!!, item.notes!!, position) }
    }

    class ItemVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.itemTitle)
        val notes: TextView = itemView.findViewById(R.id.itemNotes)
        val emailContainer: LinearLayout = itemView.findViewById(R.id.notesContainer)
    }

    fun addItem(itemNotes: ItemNotes) {
        notesList.add(itemNotes)
        notifyItemInserted(itemCount - 1)
    }

    fun updateItem(itemNotes: ItemNotes, position: Int){
        notesList[position] = itemNotes
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(title: String, notes: String, position: Int)
    }
}