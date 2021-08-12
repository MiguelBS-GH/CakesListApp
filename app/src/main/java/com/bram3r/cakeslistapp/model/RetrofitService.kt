package com.bram3r.cakeslistapp.model

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    // Get de todas las tartas
    @GET("juananthony/c51c635c877d53d0fbc7d803f23af7ea/raw/0d4454a75859e8f94834a3de257b0b69a77e0b10/cakes")
    fun getCakesList(): Call<CakeList>
}