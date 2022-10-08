package com.lucassimao.listadetarefas.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.listadetarefas.data.model.NoteModel
import com.lucassimao.listadetarefas.databinding.ItemNoteBinding
import com.lucassimao.listadetarefas.utils.showPopMenu

class NotesAdapter(
) : ListAdapter<NoteModel, NotesViewHolder>(NoteModel) {

    var deleteNote: (NoteModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.from(parent, deleteNote)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NotesViewHolder(
    private val binding: ItemNoteBinding,
    private val deleteNote: (NoteModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: NoteModel) {
        binding.apply {
            containerNote.setCardBackgroundColor(item.note_color)
            itemTitleNote.text = item.note_title
            itemDescNote.text = item.note_desc
            itemDescDate.text = "${item.note_date} - ${item.note_hour}"

            ivMore.setOnClickListener {
                showPopMenu(item, ivMore, deleteNote)
            }
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            deleteNote: (NoteModel) -> Unit,
        ): NotesViewHolder {
            return NotesViewHolder(
                ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                deleteNote
            )
        }
    }
}