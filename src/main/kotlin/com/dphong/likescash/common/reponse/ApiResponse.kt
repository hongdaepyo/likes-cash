package com.dphong.likescash.common.reponse

data class ApiResponse<S, T>(
    val status: S? = null,
    val data: T? = null
)
