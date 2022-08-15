package com.lucassimao.listadetarefas

import android.app.Application
import com.lucassimao.listadetarefas.di.databaseModule
import com.lucassimao.listadetarefas.di.repositoryModule
import com.lucassimao.listadetarefas.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}