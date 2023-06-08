package com.lucassimao.listadetarefas.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.listadetarefas.data.model.TaskModel
import com.lucassimao.listadetarefas.databinding.ItemTaskBinding
import com.lucassimao.listadetarefas.utils.showPopMenu

class TaskAdapter(private val listener: OnItemClickListener) :
    ListAdapter<TaskModel, TaskViewHolder>(TaskModel) {

    var deleteTask: (TaskModel) -> Unit = {}
    var updateTask: (TaskModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent, deleteTask, updateTask)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class TaskViewHolder(
    private val binding: ItemTaskBinding,
    private val deleteTask: (TaskModel) -> Unit,
    private val updateTask: (TaskModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TaskModel, listener: OnItemClickListener) {
        binding.apply {

            itemTitleNote.text = item.name_task
            itemCheckTask.isChecked = item.check_task

            itemCheckTask.setOnClickListener {
                listener.onItemClick(item, itemCheckTask.isChecked)
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
        ): TaskViewHolder {
            return TaskViewHolder(
                ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                deleteTask,
                updateTask
            )
        }
    }
}