package com.lucassimao.listadetarefas.utils

import android.widget.ImageView
import android.widget.PopupMenu
import com.lucassimao.listadetarefas.R
import com.lucassimao.listadetarefas.data.model.NoteModel

fun showPopMenu(item: NoteModel, more: ImageView, deleteNote: (NoteModel) -> Unit) {
    val popupMenu = PopupMenu(more.context, more)
    popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
    popupMenu.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.action_edit -> {}
            R.id.action_delete -> {
                deleteNote(item)
            }
        }
        return@setOnMenuItemClickListener true
    }
    popupMenu.show()
}