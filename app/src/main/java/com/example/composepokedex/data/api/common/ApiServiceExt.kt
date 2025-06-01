package com.example.composepokedex.data.api.common

import com.example.composepokedex.data.model.ApiError
import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.process():T  {
    if (isSuccessful) {
        return body()!! ?: throw ApiError("Data model parsing issue")
    } else {
        try {
            throw ApiError(errorBody().toString())
        } catch (ex: Exception){
            throw ApiError(ex.message.toString())
        } catch (ex: HttpException){
            throw ApiError(ex.message?:"empty httpExcemption" ,ex.code())
        }
    }
}