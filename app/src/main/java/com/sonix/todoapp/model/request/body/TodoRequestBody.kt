package com.sonix.todoapp.model.request.body

import com.sonix.todoapp.model.serializer.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

/**
 * Todoのリクエストパラメータ
 *
 * @param title Todoのタイトル
 * @param detail Todoの詳細
 * @param date Todoの日付
 */
@Serializable
data class TodoRequestBody(
    val title: String,
    val detail: String?,
    @Serializable(DateSerializer::class)
    val date: Date?
): ApiRequestBody
