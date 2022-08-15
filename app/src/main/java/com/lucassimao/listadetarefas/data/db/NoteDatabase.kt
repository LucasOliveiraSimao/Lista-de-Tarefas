package com.lucassimao.listadetarefas.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucassimao.listadetarefas.data.model.NoteModel

@Database(
    entities = [NoteModel::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDAO: NoteDAO
}