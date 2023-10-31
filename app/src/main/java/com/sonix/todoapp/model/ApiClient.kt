package com.sonix.todoapp.model

import android.content.res.Resources
import android.util.Log
import com.sonix.todoapp.R
import com.sonix.todoapp.model.request.*
import com.sonix.todoapp.model.request.body.TodoRequestBody
import com.sonix.todoapp.model.response.ApiResponse
import com.sonix.todoapp.model.response.BaseResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*

object ApiClient {
    /**
     * APIを実行するメソッド
     *
     * @param request 実行したいAPIのリクエスト
     * @param success 成功した時に実行されるクロージャ
     * @param failure 失敗した時に実行されるクロージャ
     */
    suspend inline fun <reified T: ApiResponse> call(request: ApiRequest<T>, noinline success: (T) -> Unit, noinline failure: (String) -> Unit) {
        try {
            val response = client.request<T> {
                method = request.method
                request.body?.let {
                    body = it
                }
                contentType(ContentType.Application.Json)
                header(HttpHeaders.CacheControl, "no-cache")
                url {
                    takeFrom(END_POINT)
                    encodedPath = request.path
                }
            }
            withContext(Dispatchers.Main) {
                success.invoke(response)
            }
        } catch (t: Throwable) {
            val message = t.message ?: Resources.getSystem().getString(R.string.msg_unknown_error)
            Log.d(request.javaClass.name, message)
            withContext(Dispatchers.Main) {
                failure.invoke(message)
            }
        }
    }

    val client = HttpClient(Android) {
        install(HttpTimeout) {
            requestTimeoutMillis = 10000
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                useArrayPolymorphism = true
            })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("TodoApi", message)
                }
            }
            level = LogLevel.HEADERS
        }
        HttpResponseValidator {
            validateResponse {
                if (it.status.isSuccess()) return@validateResponse

                val json = kotlinx.serialization.json.Json.Default
                val bodyText = it.content.readRemaining().readText()
                val errorResponse = try {
                    json.decodeFromString(BaseResponse.serializer(), bodyText)
                } catch (t: Throwable) {
                    Log.d(javaClass.name, t.message ?: "")
                    BaseResponse(400, Resources.getSystem().getString(R.string.msg_unknown_error))
                }
                throw ApiException(errorResponse)
            }
        }
    }

    const val END_POINT = "http://ec2-54-95-60-26.ap-northeast-1.compute.amazonaws.com:8080"
}
