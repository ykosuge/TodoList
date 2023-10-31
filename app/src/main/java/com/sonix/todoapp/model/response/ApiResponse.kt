package com.sonix.todoapp.model.response

interface ApiResponse {
    val errorCode: Int
    val errorMessage: String
}
