package com.example.ktorexample

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val title: String,
    val description: String,
    val image: String
)
