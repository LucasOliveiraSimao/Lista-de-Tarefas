package com.lucassimao.listadetarefas.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.listadetarefas.data.model.TaskModel
import com.lucassimao.listadetarefas.databinding.ItemTaskBinding
import com.lucassimao.listadetarefas.utils.showPopMenu

class TaskAdapter : ListAdapter<TaskModel, TaskViewHolder>(TaskModel) {

    var deleteTask: (TaskModel) -> Unit = {}
    var updateTask: (TaskModel) -> Unit = {}
    var clickItemCheckBox: (TaskModel, Boolean) -> Unit = { _: TaskModel, _: Boolean -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent, deleteTask, updateTask, clickItemCheckBox)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TaskViewHolder(
    private val binding: ItemTaskBinding,
    private val deleteTask: (TaskModel) -> Unit,
    private val updateTask: (TaskModel) -> Unit,
    private val clickItemCheckBox: (TaskModel, Boolean) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TaskModel) {
        binding.apply {

            itemTitleNote.text = item.name_task
            itemCheckTask.isChecked = item.check_task

            itemCheckTask.setOnClickListener {
                clickItemCheckBox(item, itemCheckTask.isChecked)
            }

            ivMore.setOnClickListener {
                showPopMenu(item, ivMore, deleteTask, updateTask)

            }
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            deleteTask: (TaskModel) -> Unit,
            updateTask: (TaskModel) -> Unit,
            clickItemCheckBox: (TaskModel, Boolean) -> Unit
        ): TaskViewHolder {
            return TaskViewHolder(
                ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                deleteTask,
                updateTask,
                clickItemCheckBox
            )
        }
    }
}