package com.may.mobiledatausage.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.may.mobiledatausage.R
import com.may.mobiledatausage.adapter.ItemAdapter
import com.may.mobiledatausage.adapter.ItemClickListener
import com.may.mobiledatausage.model.Data
import com.may.mobiledatausage.model.DataResponse
import com.may.mobiledatausage.model.YearlyRecord
import com.may.mobiledatausage.viewmodel.MainViewModel

class YearlyRecordFragment : Fragment(), ItemClickListener {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private var responseData: DataResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_yearly_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        viewModel.response.observe(this, Observer { data: DataResponse ->
            responseData = data;
            val yearlyRecords = getYearlyRecords(responseData!!.result.records)
            Log.d("YearlyRecordFragment", "Records: $yearlyRecords")
            recyclerView.adapter = ItemAdapter(yearlyRecords, this)
        })
    }

    override fun onItemClick(record: YearlyRecord) {
        Log.d("YearlyRecordFragment", "Clicked Record: $record")
        val bundle = Bundle()
        bundle.putInt("index", record.index)
        val quarterlyRecordFragment = QuarterlyRecordFragment()
        quarterlyRecordFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.overviewFragment, quarterlyRecordFragment)?.addToBackStack(null)
            ?.commit()
    }


    private fun getYearlyRecords(quarterlyRecords: List<Data.Record>): List<YearlyRecord> {
        val yearlyRecords = mutableListOf<YearlyRecord>()
        val years = listOf("2005", "2006", "2007", "2008", "2009", "2010")

        var index = 0
        for (year in years) {
            var volume = 0.0
            val records = quarterlyRecords.filter { it.quarter.contains(year) }
            for (record in records) {
                volume = volume.plus(record.volume.toDouble())
            }

            val volumeStr = String.format("%.6f", volume).toString()
            yearlyRecords.add(YearlyRecord(index, year, volumeStr))
            ++index

        }
        return yearlyRecords
    }

}