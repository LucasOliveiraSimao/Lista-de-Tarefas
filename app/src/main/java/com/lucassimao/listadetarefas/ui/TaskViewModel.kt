package com.lucassimao.listadetarefas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucassimao.listadetarefas.data.TaskRepository
import com.lucassimao.listadetarefas.data.model.TaskModel
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    fun insertTask(note: TaskModel) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun deleteTask(note: TaskModel) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }

    fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    val listAllTasks = repository.getAllTasks()

    fun deleteAllTasks() {
        viewModelScope.launch {
            repository.deleteAllTasks()
        }
    }
}