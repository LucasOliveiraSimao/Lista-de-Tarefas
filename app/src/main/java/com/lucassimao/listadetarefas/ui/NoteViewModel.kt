package com.lucassimao.listadetarefas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucassimao.listadetarefas.data.NoteRepository
import com.lucassimao.listadetarefas.data.model.TaskModel
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun insertTask(note: TaskModel) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun deleteNote(note: TaskModel) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }

    fun updateNote(note: TaskModel) {
        viewModelScope.launch {
            repository.update(note)
        }
    }

    val allNotes =  repository.getAllNotes()

}