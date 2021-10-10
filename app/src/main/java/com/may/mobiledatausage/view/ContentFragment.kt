package com.may.mobiledatausage.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.may.mobiledatausage.R
import com.may.mobiledatausage.model.Data
import com.may.mobiledatausage.model.DataResponse
import com.may.mobiledatausage.viewmodel.MainViewModel

private const val ARG_PARAM1 = "year"

class ContentFragment : Fragment() {
    private var year: String? = null

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            year = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvYear = view.findViewById<TextView>(R.id.tvYear)
        tvYear.text = year

        val tvQuarter = view.findViewById<TextView>(R.id.quarter)

        viewModel.response.observe(this, Observer { data: DataResponse ->
            val quarterList = year?.let { getYearlyRecords(it, data!!.result.records) }
            var text = ""
            quarterList?.let {
                for (item in quarterList) {
                    val quarterStr = item.quarter.split("-")[1].replace("Q", "Quarter ")
                    val volumeStr = String.format("%.6f", item.volume.toDouble()).toString()
                    text += "$quarterStr: $volumeStr\n\n"
                }
            }
            tvQuarter.text = text
        })
    }

    private fun getYearlyRecords(
        year: String,
        quarterlyRecords: List<Data.Record>
    ): List<Data.Record> {
        val quarterList = quarterlyRecords.filter { it.quarter.contains(year) }
        Log.d("ContentFragment", "Seleceted Records: $quarterList")
        return quarterList
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            Log.d(
                "ContentFragment",
                "Active Page: $year"
            )
        }
    }
}