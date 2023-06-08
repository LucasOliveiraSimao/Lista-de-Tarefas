package com.lucassimao.listadetarefas.ui.home

import com.lucassimao.listadetarefas.data.model.TaskModel

interface OnItemClickListener {
    fun onItemClick(task: TaskModel, isChecked: Boolean)
}