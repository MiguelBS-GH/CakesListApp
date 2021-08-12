package com.bram3r.cakeslistapp.cakesList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bram3r.cakeslistapp.model.Cake
import com.bram3r.cakeslistapp.model.CakeList
import com.bram3r.cakeslistapp.model.Retrofit
import com.bram3r.cakeslistapp.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Error

class CakesListViewModel : ViewModel() {

    private val _cakeList = MutableLiveData<List<Cake>>()
    val cakeList: LiveData<List<Cake>> get() = _cakeList

    // Comprobar si hay un error y lanzar Toast
    val status = MutableLiveData<String?>()

    // Descargar datos y actualizar lista de tartas
    fun updateCakesList() {
        val retrofitInstance = Retrofit.getInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.getCakesList() // Llamada a todas las tartas
        try {
            call.enqueue(object : Callback<CakeList> {
                override fun onResponse(call: Call<CakeList>, response: Response<CakeList>) {
                    if (response.isSuccessful) {
                        Log.e("TAG", "isSuccessful")
                        // Lista de tartas (eliminadas repetidas y ordenadas por nombre)
                        _cakeList.value = response.body()!!.distinctBy { it.title }.sortedBy { it.title }
                    } else {
                        Log.e("TAG", "Fail: Respuesta fallida")
                        status.value = "Respuesta fallida"
                    }
                }
                override fun onFailure(call: Call<CakeList>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.message}")
                    status.value = "Fallo al descargar la lista"
                }

            })
        }catch (e: Error) {
            status.value = "Error al acceder a los datos"
            Log.e("TAG", "${e.message}")
        }

    }
}