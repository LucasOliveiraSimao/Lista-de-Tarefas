package com.lucassimao.listadetarefas.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lucassimao.listadetarefas.data.model.NoteModel

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteModel)

    @Query("SELECT * FROM note_table ORDER BY note_id ASC")
   fun getAllNotes(): LiveData<List<NoteModel>>

    @Delete
    suspend fun deleteNote(note: NoteModel)

    @Update
    suspend fun updateNote(noteModel: NoteModel)
}