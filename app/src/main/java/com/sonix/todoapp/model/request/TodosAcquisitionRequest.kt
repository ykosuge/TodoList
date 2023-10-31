package com.sonix.todoapp.model.request

import com.sonix.todoapp.model.request.body.ApiRequestBody
import com.sonix.todoapp.model.response.TodosAcquisitionResponse
import io.ktor.http.*

/**
 * Todo一覧の取得リクエスト
 *
 * [TodosAcquisitionResponse.todos] が取得されるTodoの一覧
 */
class TodosAcquisitionRequest: ApiRequest<TodosAcquisitionResponse> {
    override val path: String = "/todos"
    override val method: HttpMethod = HttpMethod.Get
    override val body: ApiRequestBody? = null
}
