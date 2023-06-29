package com.lucassimao.listadetarefas.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

fun enableButtonWhenEditTextHasText(editText: EditText?, button: Button?) {

    editText!!.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(char: CharSequence?, start: Int, before: Int, count: Int) {
            val isEditTextEmpty = char.isNullOrBlank()

            button!!.isEnabled = !isEditTextEmpty
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })
}