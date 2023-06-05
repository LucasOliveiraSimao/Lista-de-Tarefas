package com.lucassimao.listadetarefas.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lucassimao.listadetarefas.data.model.TaskModel

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: TaskModel)

    @Query("SELECT * FROM note_table ORDER BY note_id DESC")
   fun getAllNotes(): LiveData<List<TaskModel>>

    @Delete
    suspend fun deleteNote(note: TaskModel)

    @Update
    suspend fun updateNote(noteModel: TaskModel)
}