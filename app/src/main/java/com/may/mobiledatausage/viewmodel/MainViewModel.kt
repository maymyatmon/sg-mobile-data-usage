package com.may.mobiledatausage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.may.mobiledatausage.model.DataResponse
import com.may.mobiledatausage.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _reponse = MutableLiveData<DataResponse>()
    val response: LiveData<DataResponse>
        get() = _reponse

    init {
        getApiResponse()
    }

    private fun getApiResponse() {

        val apiInterface = ApiInterface.create().getData()

        apiInterface.enqueue(object : Callback<DataResponse> {

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                val dataResponse = response.body()!!
                Log.d("Response", "onResponse: $dataResponse")
                if (dataResponse.success) {
                    _reponse.value = dataResponse

                } else {
                    Log.d("Response", "Something went wrong ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.d("Response", "Something went wrong $t")
            }
        })
    }
}