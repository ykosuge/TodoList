package com.sonix.todoapp.model

import com.sonix.todoapp.model.serializer.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Todo(
    val id: Int,
    /** タイトル */
    val title: String,
    /** 詳細 */
    val detail: String?,
    /** 日付 */
    @Serializable(DateSerializer::class)
    val date: Date?
): java.io.Serializable
