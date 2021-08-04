package com.example.newsapp.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers


/**
 * Set Default dataSource "localDS"
 * Get Data From Remote Data Source
 * Save fresh Data in local dataSource
 */

fun <T, A> performGetOperation(
    dataBaseQuery: () -> LiveData<T>, // functions from Local data source
    netWorkCall: suspend () -> Resource<A>, // functions from remote data source
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =

    liveData(Dispatchers.IO) {
        //Start with loading state
        emit(Resource.loading())
        // Attach this scope with local database live data scope
        // set as a default data source
        val source = dataBaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        // Get Data From Remote Data source "API"
        val responseStatus = netWorkCall.invoke()
        // if Data success
        if (responseStatus.status == Resource.Status.SUCCESS) {
            //Save data to local DataBase
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == Resource.Status.ERROR) {
            //Show Message
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }
