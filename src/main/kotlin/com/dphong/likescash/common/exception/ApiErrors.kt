package com.dphong.likescash.common.exception

enum class ApiErrors(private val message: String) : ErrorCode {

    MEMBER_NOT_FOUND("Member not found"),
    ;
    override fun getMessage(): String {
        return message
    }
}
