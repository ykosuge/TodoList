package com.sonix.todoapp.model.request

import com.sonix.todoapp.model.request.body.ApiRequestBody
import com.sonix.todoapp.model.response.ApiResponse
import io.ktor.http.*

interface ApiRequest<T: ApiResponse> {
    val path: String
    val method: HttpMethod
    val body: ApiRequestBody?
}
