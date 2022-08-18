package com.lucassimao.listadetarefas.di

import com.lucassimao.listadetarefas.data.NoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { NoteRepository(get()) }
}