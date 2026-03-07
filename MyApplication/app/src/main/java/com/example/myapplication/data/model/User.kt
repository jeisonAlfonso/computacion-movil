package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val email: String,
    val phone: String,
    val image: String,
    val company: Company,
    val university: String? = null,
    val birthDate: String? = null,
    val height: Double? = null,
    val weight: Double? = null
)

