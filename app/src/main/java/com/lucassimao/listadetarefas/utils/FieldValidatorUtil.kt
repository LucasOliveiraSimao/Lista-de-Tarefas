package com.lucassimao.listadetarefas.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun isTaskFieldEmpty(editText: EditText?): Boolean {
    return editText?.text.toString().isEmpty()
}

fun emptyFieldMessage(context: Context?, string: String): Toast = Toast.makeText(
    context,
    string,
    Toast.LENGTH_SHORT
)