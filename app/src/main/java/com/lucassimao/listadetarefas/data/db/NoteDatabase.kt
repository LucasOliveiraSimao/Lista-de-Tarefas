package com.lucassimao.listadetarefas.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucassimao.listadetarefas.data.model.TaskModel

@Database(
    entities = [TaskModel::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDAO: NoteDAO
}