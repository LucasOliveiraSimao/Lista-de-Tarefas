package com.lucassimao.listadetarefas.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.listadetarefas.R
import com.lucassimao.listadetarefas.data.model.TaskModel
import com.lucassimao.listadetarefas.databinding.ItemNoteBinding
import com.lucassimao.listadetarefas.utils.CODE_COLOR_DEEP_PURPLE
import com.lucassimao.listadetarefas.utils.CODE_COLOR_GREEN
import com.lucassimao.listadetarefas.utils.CODE_COLOR_RED
import com.lucassimao.listadetarefas.utils.showPopMenu

class NotesAdapter : ListAdapter<TaskModel, NotesViewHolder>(TaskModel) {

    var deleteNote: (TaskModel) -> Unit = {}
    var updateNote: (TaskModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.from(parent, deleteNote, updateNote)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NotesViewHolder(
    private val binding: ItemNoteBinding,
    private val deleteNote: (TaskModel) -> Unit,
    private val updateNote: (TaskModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    fun bind(item: TaskModel) {
        binding.apply {
            containerNote.setCardBackgroundColor(item.note_color)

            Log.i("Test", "${item.note_color} - ${item.note_title}")

            if (item.note_color == CODE_COLOR_DEEP_PURPLE) {
                itemTitleNote.setTextColor(itemView.context.getColor(R.color.white))
                itemDescNote.setTextColor(itemView.context.getColor(R.color.white))
                itemDescDate.setTextColor(itemView.context.getColor(R.color.white))
            }

            if (item.note_color == CODE_COLOR_RED) {
                itemTitleNote.setTextColor(itemView.context.getColor(R.color.white))
                itemDescNote.setTextColor(itemView.context.getColor(R.color.white))
                itemDescDate.setTextColor(itemView.context.getColor(R.color.white))
            }
            
            if (item.note_color == CODE_COLOR_GREEN) {
                itemTitleNote.setTextColor(itemView.context.getColor(R.color.white))
                itemDescNote.setTextColor(itemView.context.getColor(R.color.white))
                itemDescDate.setTextColor(itemView.context.getColor(R.color.white))
            }

            itemTitleNote.text = item.note_title
            itemDescNote.text = item.note_desc
            itemDescDate.text = "${item.note_date} - ${item.note_hour}"

            ivMore.setOnClickListener {
                showPopMenu(item, ivMore, deleteNote, updateNote)

            }

        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            deleteNote: (TaskModel) -> Unit,
            updateNote: (TaskModel) -> Unit,
        ): NotesViewHolder {
            return NotesViewHolder(
                ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                deleteNote,
                updateNote
            )
        }
    }
}