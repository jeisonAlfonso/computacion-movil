package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val name: String,
    val title: String,
    val department: String
)

