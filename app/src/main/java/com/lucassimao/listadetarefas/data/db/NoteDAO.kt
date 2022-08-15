package com.lucassimao.listadetarefas.data.db

import androidx.room.*
import com.lucassimao.listadetarefas.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteModel)

    @Query("SELECT * FROM note_table ORDER BY note_id ASC")
    fun getAllNotes(): Flow<List<NoteModel>>

    @Delete
    suspend fun deleteNote(note: NoteModel)

    @Update
    suspend fun updateNote(noteModel: NoteModel)
}