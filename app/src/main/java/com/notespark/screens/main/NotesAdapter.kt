package com.notespark.screens.main

import android.view.*
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

        holder.notesContainer.setOnClickListener { itemClick.onItemClick(item.title!!, item.notes!!, position) }
    }

    class ItemVH(itemView: View): RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        val title: TextView = itemView.findViewById(R.id.itemTitle)
        val notes: TextView = itemView.findViewById(R.id.itemNotes)
        val notesContainer: LinearLayout = itemView.findViewById(R.id.notesContainer)

        init {
            notesContainer.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, p1: View?, p2: ContextMenu.ContextMenuInfo?) {
            menu?.add(adapterPosition, 101, 0, "Delete")
        }
    }

    fun updateList(list: MutableList<ItemNotes>){
        this.notesList = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(title: String, notes: String, position: Int)
    }
}