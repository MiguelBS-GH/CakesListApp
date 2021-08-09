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

class CakesListViewModel : ViewModel() {

    private val _cakeList = MutableLiveData<List<Cake>>()
    val cakeList: LiveData<List<Cake>> get() = _cakeList

    fun updateCakesList() {
        val retrofitInstance = Retrofit.getInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.getCakesList()
        call.enqueue(object : Callback<CakeList> {
            override fun onResponse(call: Call<CakeList>, response: Response<CakeList>) {
                if (response.isSuccessful) {
                    Log.e("TAG", "isSuccessful")
                    _cakeList.value = response.body()!!.sortedBy { it.title }.distinctBy { it.title }

                } else {
                    Log.e("TAG", "FAIL")
                }
            }

            override fun onFailure(call: Call<CakeList>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }

        })
    }
}