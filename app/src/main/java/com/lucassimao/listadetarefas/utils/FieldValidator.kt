package com.lucassimao.listadetarefas.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun isEmptyNoteField(binding: EditText): Boolean {
    val note = binding.text.toString()

    if (note.isNotEmpty()) {
        return true
    }
    if (note.isNotBlank()) {
        return true
    }

    return false

}

fun emptyFieldMessage(context: Context?, string: String): Toast = Toast.makeText(
    context,
    string,
    Toast.LENGTH_SHORT
)