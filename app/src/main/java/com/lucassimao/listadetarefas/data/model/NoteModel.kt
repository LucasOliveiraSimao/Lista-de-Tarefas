package com.lucassimao.listadetarefas.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var note_id: Int,
    val note_title: String,
    val note_desc: String
)