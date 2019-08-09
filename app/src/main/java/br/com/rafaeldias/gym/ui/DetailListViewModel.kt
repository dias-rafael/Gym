package br.com.rafaeldias.gym.ui

import br.com.rafaeldias.gym.base.BaseViewModel
import br.com.rafaeldias.gym.model.Gym

class DetailListViewModel: BaseViewModel(){

    val detailListAdapter: DetailListAdapter = DetailListAdapter()

    fun setGym(detalhes: Gym? = null) {
        detailListAdapter.updateDetailList(detalhes!!, detalhes!!.activities)
    }

}