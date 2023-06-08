package com.lucassimao.listadetarefas.data

import com.lucassimao.listadetarefas.data.db.TaskDAO
import com.lucassimao.listadetarefas.data.model.TaskModel

class TaskRepository(private val dao: TaskDAO) {
    suspend fun insert(task: TaskModel) {
        dao.insertTask(task)
    }

    suspend fun delete(task: TaskModel) {
        dao.deleteTask(task)
    }

    suspend fun update(task: TaskModel) {
        dao.updateTask(task)
    }

    fun getAllTasks() = dao.getAllTasks()

}