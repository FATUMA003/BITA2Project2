package com.example.projecttracker

data class User(
    val id: Int? = null,
    val fullName: String,
    val email: String,
    val password: String,
    val role: String
)
