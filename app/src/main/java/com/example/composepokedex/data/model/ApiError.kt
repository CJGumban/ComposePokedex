package com.example.composepokedex.data.model

class ApiError(private val msg: String = "Api Error", private val statusCode: Int = 0): Throwable(msg){
    fun errorMessage() = msg
    fun statusCode() = statusCode
}