package br.com.rafaeldias.gym.ui

import android.arch.lifecycle.MutableLiveData
import br.com.rafaeldias.gym.base.BaseViewModel
import br.com.rafaeldias.gym.model.Activity

class DetailViewModel: BaseViewModel() {
    private val detalheModalidade = MutableLiveData<String>()

    fun bind(gym: Activity){
        detalheModalidade.value = gym.title
    }

    fun getDetalheModalidade(): MutableLiveData<String> {
        return detalheModalidade
    }
}