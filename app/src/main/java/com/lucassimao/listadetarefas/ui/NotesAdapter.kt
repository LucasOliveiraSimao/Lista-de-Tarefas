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
    private val onItemClick: (NoteModel) -> Unit,
    private val onLongItemClick: (NoteModel) -> Unit
) : ListAdapter<NoteModel, NotesViewHolder>(NoteModel) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.from(parent, onItemClick, onLongItemClick)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NotesViewHolder(
    private val binding: ItemNoteBinding,
    private val onItemClick: (NoteModel) -> Unit,
    private val onLongItemClick: (NoteModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: NoteModel) {
        binding.apply {
            containerNote.setCardBackgroundColor(item.note_color)
            itemTitleNote.text = item.note_title
            itemDescNote.text = item.note_desc
            itemDescDate.text = "${item.note_date} - ${item.note_hour}"

            ivMore.setOnClickListener {
                showPopMenu(item, ivMore)
            }
            itemView.setOnClickListener {
                onLongItemClick(item)
                return@setOnClickListener
            }
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            onItemClick: (NoteModel) -> Unit,
            onLongItemClick: (NoteModel) -> Unit
        ): NotesViewHolder {
            return NotesViewHolder(
                ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onItemClick,
                onLongItemClick
            )
        }
    }
}