package com.lucassimao.listadetarefas.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lucassimao.listadetarefas.data.model.TaskModel

@Dao
interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: TaskModel)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllTasks(): LiveData<List<TaskModel>>

    @Delete
    suspend fun deleteTask(task: TaskModel)

    @Update
    suspend fun updateTask(task: TaskModel)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllTasks()
}