package com.example.projecttracker.model

data class Project(
    val projectID: Long? = null,
    val userID: Long,
    val groupName: String,
    val projectName: String,
    val description: String,
    val date: String
)
