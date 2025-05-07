package com.example.jobtrac.model

data class SubmittedForm(
    val name: String,
    val email: String,
    val notes: String,
    val fileUri: String? = null
)
