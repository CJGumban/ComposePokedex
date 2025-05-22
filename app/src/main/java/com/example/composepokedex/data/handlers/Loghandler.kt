package com.example.composepokedex.data.handlers

import timber.log.Timber

object Loghandler {
    private const val tag = "POKEMON_APP"

    fun e(ex: Exception){
        Timber.tag(tag).e(ex)
    }

    fun e(msg: String){
        Timber.tag(tag).e(msg)
    }

    fun d(msg: String){
        Timber.tag(tag).d(msg)
    }

    fun w(ex: Exception){
        Timber.tag(tag).w(ex)
    }

    fun w(msg: String){
        Timber.tag(tag).w(msg)
    }
}