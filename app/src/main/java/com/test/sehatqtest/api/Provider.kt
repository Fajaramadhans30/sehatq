package com.test.sehatqtest.api

object Provider {
    fun dataProviderRepository():Repository{
        return  Repository(Service.create())
    }
}