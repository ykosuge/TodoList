package com.sonix.todoapp.model.response

import com.sonix.todoapp.model.Todo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Todo一覧の取得レスポンス */
@Serializable
data class TodosAcquisitionResponse(
    /** Todoの一覧 */
    val todos: List<Todo>?,
    @SerialName("error_code")
    override val errorCode: Int,
    @SerialName("error_message")
    override val errorMessage: String
): ApiResponse
