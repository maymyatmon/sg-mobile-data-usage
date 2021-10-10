package com.may.mobiledatausage.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("resource_id")
    val resourceId: String,
    val records: List<Record>,
) {
    data class Record(
        val quarter: String,
        @SerializedName("volume_of_mobile_data")
        val volume: String
    )
}