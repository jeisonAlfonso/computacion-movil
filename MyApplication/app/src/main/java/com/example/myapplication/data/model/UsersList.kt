package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UsersList(
    val users: List<User>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

