package com.lucassimao.listadetarefas.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.listadetarefas.data.model.TaskModel
import com.lucassimao.listadetarefas.databinding.ItemTaskBinding

class TaskAdapter : ListAdapter<TaskModel, TaskViewHolder>(TaskModel) {

    var updateTask: (TaskModel) -> Unit = {}
    var clickItemCheckBox: (TaskModel, Boolean) -> Unit = { _: TaskModel, _: Boolean -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent, updateTask, clickItemCheckBox)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TaskViewHolder(
    private val binding: ItemTaskBinding,
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

            ivEdit.setOnClickListener {
                updateTask(item)
            }
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            updateTask: (TaskModel) -> Unit,
            clickItemCheckBox: (TaskModel, Boolean) -> Unit
        ): TaskViewHolder {
            return TaskViewHolder(
                ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                updateTask,
                clickItemCheckBox
            )
        }
    }
}