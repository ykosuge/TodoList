package com.sonix.todoapp.model

import com.sonix.todoapp.model.response.BaseResponse
import java.lang.RuntimeException

class ApiException(errorResponse: BaseResponse): RuntimeException(errorResponse.errorMessage, null)
