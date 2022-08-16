package com.lucassimao.listadetarefas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.listadetarefas.data.model.NoteModel
import com.lucassimao.listadetarefas.databinding.ItemNoteBinding

class NotesAdapter : ListAdapter<NoteModel, NotesViewHolder>(NoteModel) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NotesViewHolder(
    private val binding: ItemNoteBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: NoteModel) {
        binding.apply {
            itemTitleNote.text = item.note_title
            itemDescNote.text = item.note_desc
        }
    }

    companion object {
        fun from(
            parent: ViewGroup
        ): NotesViewHolder {
            return NotesViewHolder(
                ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}