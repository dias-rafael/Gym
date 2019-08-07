package br.com.rafaeldias.gym.injection.component

import br.com.rafaeldias.gym.injection.module.NetworkModule
import br.com.rafaeldias.gym.ui.GymListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(gymListViewModel: GymListViewModel)

    //fun injectDetail(activityListViewModel: ActivityListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}