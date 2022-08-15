package com.lucassimao.listadetarefas.di

import android.app.Application
import androidx.room.Room
import com.lucassimao.listadetarefas.data.db.NoteDAO
import com.lucassimao.listadetarefas.data.db.NoteDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideNoteDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    fun provideNoteDAO(database: NoteDatabase): NoteDAO {
        return database.noteDAO
    }

    single { provideNoteDatabase(androidApplication()) }
    single { provideNoteDAO(get()) }
}