package br.com.rafaeldias.gym.base

import android.arch.lifecycle.ViewModel
import br.com.rafaeldias.gym.injection.component.DaggerViewModelInjector
import br.com.rafaeldias.gym.injection.component.ViewModelInjector
import br.com.rafaeldias.gym.injection.module.NetworkModule
import br.com.rafaeldias.gym.ui.DetailListViewModel
import br.com.rafaeldias.gym.ui.GymListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is GymListViewModel -> injector.inject(this)
            is DetailListViewModel -> injector.injectDetail(this)
        }
    }
}