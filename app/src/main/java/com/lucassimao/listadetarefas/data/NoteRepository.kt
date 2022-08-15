package com.lucassimao.listadetarefas.data

import com.lucassimao.listadetarefas.data.db.NoteDAO
import com.lucassimao.listadetarefas.data.model.NoteModel

class NoteRepository(private val dao: NoteDAO) {
    suspend fun insert(note: NoteModel) {
        dao.insertNote(note)
    }

    suspend fun delete(note: NoteModel) {
        dao.deleteNote(note)
    }

    suspend fun update(note: NoteModel) {
        dao.updateNote(note)
    }

    fun getAllNotes() = dao.getAllNotes()

}