package com.department.guide.global.httpStatus

enum class ResultCode(val msg: String) {

        SUCCESS("success"),
        ERROR("error")
}

data class BaseResponse<T> (

        val resultCode: String = ResultCode.SUCCESS.name,
        val data: T? = null,
        val message: String = ResultCode.ERROR.msg,
)