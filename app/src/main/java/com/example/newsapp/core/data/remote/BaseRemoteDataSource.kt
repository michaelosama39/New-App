package com.example.newsapp.core.data.remote

import com.example.newsapp.core.utils.Resource
import retrofit2.Response

abstract class BaseRemoteDataSource {
   protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(" ${e.message}")
        }
    }


    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed due to : $message")
    }


}