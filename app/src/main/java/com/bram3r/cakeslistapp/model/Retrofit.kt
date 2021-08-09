package com.bram3r.cakeslistapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    companion object {

        val URL_BASE = "https://gist.githubusercontent.com/"

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}