package com.may.mobiledatausage.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.may.mobiledatausage.R
import com.may.mobiledatausage.adapter.ItemAdapter
import com.may.mobiledatausage.model.Data
import com.may.mobiledatausage.model.DataResponse
import com.may.mobiledatausage.model.YearlyRecord
import com.may.mobiledatausage.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YearlyRecordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.yearly_record_fragment, container, false)

        loadData(view)

        return view;
    }

    private fun loadData(view: View)
    {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val apiInterface = ApiInterface.create().getData()

        apiInterface.enqueue(object : Callback<DataResponse> {

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                val dataResponse = response.body()!!
                Log.d("Response", "onResponse: $dataResponse")
                if (dataResponse.success) {
                    val quarterlyRecords = dataResponse.result.records
                    recyclerView.adapter= ItemAdapter(getYearlyRecords(quarterlyRecords))

                } else {
                    Log.d("Response", "Something went wrong ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.d("Response", "Something went wrong $t")
            }
        })
    }

    private fun getYearlyRecords(quarterlyRecords: List<Data.Record>): List<YearlyRecord> {
        val yearlyRecords = mutableListOf<YearlyRecord>()
        val years = listOf("2004", "2005", "2006", "2007", "2008", "2009", "2010")

        for (year in years) {
            var volume = 0.0
            val records = quarterlyRecords.filter { it.quarter.contains(year) }
            Log.d("Records", "Records: $records")
            for (record in records) {
                volume = volume.plus(record.volume.toDouble())
            }

            val volumeStr = String.format("%.6f", volume).toString()
            yearlyRecords.add(YearlyRecord(year, volumeStr))

        }
        return yearlyRecords
    }

}