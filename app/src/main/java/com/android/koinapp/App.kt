package com.android.koinapp

import android.app.Application
import com.android.koinapp.di.appModule
import com.android.koinapp.di.remoteWebSourceModule
import com.android.koinapp.di.repositoryModule
import com.android.koinapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {
/*    val mainModule: Module = module{
        viewModel {  }
    }*/

    override fun onCreate() {
        super.onCreate()
        startKoin {

            printLogger()
            androidLogger()

            androidContext(this@App)

            modules(
                listOf(
                    appModule,
                    viewModelModule,
                    repositoryModule,
                    remoteWebSourceModule
                )
            )
        }

    }
}