package com.lucassimao.listadetarefas.utils

import java.util.*

fun formatDate(day: Int, month: Int, year: Int): String {
    val myDay = if (day in 1..9) "0$day" else day
    return "$myDay/${month + 1}/$year"
}

private val date = Calendar.getInstance()
val setupYear = date.get(Calendar.YEAR)
val setupMonth = date.get(Calendar.MONTH)
val setupDay = date.get(Calendar.DAY_OF_MONTH)