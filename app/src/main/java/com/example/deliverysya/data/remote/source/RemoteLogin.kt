package com.example.deliverysya.data.remote.source

import com.example.deliverysya.data.remote.source.Constants.PASSWORD
import com.example.deliverysya.data.remote.source.Constants.USER
import com.google.gson.annotations.SerializedName



data class RemoteLogin(
    @SerializedName(USER) val user: String,
    @SerializedName(PASSWORD) val password: String
)
