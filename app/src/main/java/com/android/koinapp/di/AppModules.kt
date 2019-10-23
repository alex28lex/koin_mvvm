package com.android.koinapp.di

import com.android.koinapp.model.GitRepository
import com.android.koinapp.model.IGitRepository
import com.android.view.MainViewModel
import com.android.view.UsersAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * App Components
 */
val viewModelModule: Module = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule: Module = module {
    //single { GitRepository(get()) } bind IGitRepository::class
    single<IGitRepository> { GitRepository(restClient = get()) }
}
val appModule: Module = module {
    factory { UsersAdapter() }
}


