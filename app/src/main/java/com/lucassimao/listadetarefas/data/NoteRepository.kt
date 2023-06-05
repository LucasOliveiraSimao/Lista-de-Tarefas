package com.lucassimao.listadetarefas.data

import com.lucassimao.listadetarefas.data.db.NoteDAO
import com.lucassimao.listadetarefas.data.model.TaskModel

class NoteRepository(private val dao: NoteDAO) {
    suspend fun insert(note: TaskModel) {
        dao.insertNote(note)
    }

    suspend fun delete(note: TaskModel) {
        dao.deleteNote(note)
    }

    suspend fun update(note: TaskModel) {
        dao.updateNote(note)
    }

    fun getAllNotes() = dao.getAllNotes()

}