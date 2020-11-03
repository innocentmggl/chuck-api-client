package com.innocent.chuck.jokes.commons.dto

import com.google.gson.annotations.SerializedName

data class Joke(@SerializedName("id") val id: String,
                @SerializedName("value") val value: String,
                @SerializedName("url") val url: String,
                @SerializedName("icon_url") val iconUrl: String,
                @SerializedName("created_at") val createdDate: String,
                )