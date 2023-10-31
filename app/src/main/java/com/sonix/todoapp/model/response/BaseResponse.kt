package com.sonix.todoapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** 基本のレスポンス */
@Serializable
data class BaseResponse(
    /** エラーコード */
    @SerialName("error_code")
    override val errorCode: Int,
    /** エラーメッセージ */
    @SerialName("error_message")
    override val errorMessage: String
) : ApiResponse
