package com.dphong.likescash.common.reponse

import org.springframework.http.ResponseEntity

data class ApiResponse<S, T>(
    val status: S? = null,
    val data: T? = null
)

data class StatusResult<S>(val status: S? = null) {

    fun toResponseEntity(): ResponseEntity<StatusResult<S>> = ResponseEntity.ok(this)
}
data class DataResult<T>(val data: T? = null) {

    fun toResponseEntity(): ResponseEntity<DataResult<T>> = ResponseEntity.ok(this)
}
data class StatusDataResult<S, T>(val status: S? = null, val data: T? = null) {

    fun toResponseEntity(): ResponseEntity<StatusDataResult<S, T>> = ResponseEntity.ok(this)
}
