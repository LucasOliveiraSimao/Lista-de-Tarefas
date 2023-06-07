package com.lucassimao.listadetarefas.di

import com.lucassimao.listadetarefas.data.TaskRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { TaskRepository(get()) }
}