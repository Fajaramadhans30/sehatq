package com.test.sehatqtest.api

import com.test.sehatqtest.model.model2.Data
import io.reactivex.Observable

class Repository (private val service: Service) {
    fun getData(): Observable<List<Data>> {
        return service.getData()
    }
}