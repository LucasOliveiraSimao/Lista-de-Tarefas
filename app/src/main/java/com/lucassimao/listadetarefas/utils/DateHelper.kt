package com.lucassimao.listadetarefas.utils

import java.text.SimpleDateFormat
import java.util.*

private val local = Locale("pt", "BR")

fun Date.format(): String {
    return SimpleDateFormat("dd/MM/yyyy", local).format(this)
}