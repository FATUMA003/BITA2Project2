package com.example.projecttracker.model

data class Upload(
    val id: Long? = null,
    val projectID: Long,
    val githubLink: String
)
