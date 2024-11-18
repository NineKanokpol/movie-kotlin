package com.example.testappdevandroid.Models

import com.google.gson.annotations.SerializedName

data class KeyWordModels(
    @SerializedName("id"       ) var id       : Int?                = null,
    @SerializedName("keywords" ) var keywords : ArrayList<Keywords> = arrayListOf()
)
data class Keywords (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null

)
