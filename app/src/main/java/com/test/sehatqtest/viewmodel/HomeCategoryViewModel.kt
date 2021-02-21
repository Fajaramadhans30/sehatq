package com.test.sehatqtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.sehatqtest.api.Repository
import com.test.sehatqtest.model.model2.Category
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class HomeCategoryViewModel(
    private val compositeDisposable: CompositeDisposable,
    private val repository: Repository,
    private val backgroundScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModel() {
    private var listCategory = MutableLiveData<MutableList<Category>>()

    fun setListCategory() {
        compositeDisposable.add(
            repository.getData()
                .observeOn(backgroundScheduler)
                .subscribeOn(mainScheduler)
                .subscribe({ CategoryViewModel ->
                    println("error message1 " + CategoryViewModel.get(0).datum?.categoryData)
                    listCategory.postValue(CategoryViewModel[0].datum?.categoryData as ArrayList<Category>?)

                }, { error ->
                    println("error message " + error.message)
                    listCategory.postValue(null)
                }
                )
        )
    }

    fun getListCategory(): LiveData<MutableList<Category>> {
        return listCategory
    }

}

class ViewModelCategoryFactory(
    private val compositeDisposable: CompositeDisposable,
    private val repository: Repository,
    private val backgroundScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModelProvider.NewInstanceFactory() {
    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeCategoryViewModel(
            compositeDisposable,
            repository,
            backgroundScheduler,
            mainScheduler
        ) as T
    }
}