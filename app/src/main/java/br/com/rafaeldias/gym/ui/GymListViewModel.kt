package br.com.rafaeldias.gym.ui

import android.arch.lifecycle.MutableLiveData
import android.view.View
import br.com.rafaeldias.gym.R
import br.com.rafaeldias.gym.base.BaseViewModel
import br.com.rafaeldias.gym.model.Gym
import br.com.rafaeldias.gym.network.GymApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GymListViewModel: BaseViewModel(){

    @Inject
    lateinit var gymApi: GymApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadGyms() }

    val gymListAdapter: GymListAdapter = GymListAdapter()

    private lateinit var subscription: Disposable

    init{
        loadGyms()
    }

    private fun loadGyms(){
        subscription = gymApi.getGyms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveGymListStart() }
            .doOnTerminate { onRetrieveGymListFinish() }
            .subscribe(
                { result -> onRetrieveGymListSuccess(result)},
                { onRetrieveGymListError() }
            )
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrieveGymListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveGymListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveGymListSuccess(gymList:List<Gym>){
        gymListAdapter.updateGymList(gymList)
    }

    private fun onRetrieveGymListError(){
        errorMessage.value = R.string.gym_error
    }
}