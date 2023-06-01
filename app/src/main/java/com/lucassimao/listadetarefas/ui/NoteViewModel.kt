package com.lucassimao.listadetarefas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucassimao.listadetarefas.data.NoteRepository
import com.lucassimao.listadetarefas.data.model.NoteModel
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun insertTask(note: NoteModel) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun deleteNote(note: NoteModel) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }

    fun updateNote(note: NoteModel) {
        viewModelScope.launch {
            repository.update(note)
        }
    }

    val allNotes =  repository.getAllNotes()

}