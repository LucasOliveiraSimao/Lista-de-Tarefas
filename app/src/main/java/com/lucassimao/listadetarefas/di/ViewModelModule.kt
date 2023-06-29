package com.lucassimao.listadetarefas.di

import com.lucassimao.listadetarefas.ui.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TaskViewModel(get()) }
}