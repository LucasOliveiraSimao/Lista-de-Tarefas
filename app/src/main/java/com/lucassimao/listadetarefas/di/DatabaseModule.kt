package com.lucassimao.listadetarefas.di

import android.app.Application
import androidx.room.Room
import com.lucassimao.listadetarefas.data.db.TaskDAO
import com.lucassimao.listadetarefas.data.db.TaskDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideNoteDatabase(application: Application): TaskDatabase {
        return Room.databaseBuilder(
            application,
            TaskDatabase::class.java,
            "note_database"
        ).build()
    }

    fun provideNoteDAO(database: TaskDatabase): TaskDAO {
        return database.noteDAO
    }

    single { provideNoteDatabase(androidApplication()) }
    single { provideNoteDAO(get()) }
}