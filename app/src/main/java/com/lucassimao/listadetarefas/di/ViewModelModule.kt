package com.lucassimao.listadetarefas.di

import com.lucassimao.listadetarefas.ui.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NoteViewModel(get()) }
}