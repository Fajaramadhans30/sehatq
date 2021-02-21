package com.test.sehatqtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.sehatqtest.api.Repository
import com.test.sehatqtest.model.model2.ProductPromo
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class HomeProductViewModel(
    private val compositeDisposable: CompositeDisposable,
    private val repository: Repository,
    private val backgroundScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModel() {
    private var listProduct = MutableLiveData<MutableList<ProductPromo>>()

    fun setListProduct() {
        compositeDisposable.add(
            repository.getData()
                .observeOn(backgroundScheduler)
                .subscribeOn(mainScheduler)
                .subscribe({ ProductViewModel ->
                    println("error message1 " + ProductViewModel.get(0).datum?.productPromoData)
                    listProduct.postValue(ProductViewModel[0].datum?.productPromoData as ArrayList<ProductPromo>?)

                }, { error ->
                    println("error message " + error.message)
                    listProduct.postValue(null)
                }
                )
        )
    }

    fun getListProduct(): LiveData<MutableList<ProductPromo>> {
        return listProduct
    }

}

class ViewModelProductFactory(
    private val compositeDisposable: CompositeDisposable,
    private val repository: Repository,
    private val backgroundScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModelProvider.NewInstanceFactory() {
    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeProductViewModel(
            compositeDisposable,
            repository,
            backgroundScheduler,
            mainScheduler
        ) as T
    }
}