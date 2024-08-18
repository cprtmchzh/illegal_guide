package com.department.guide.global.exception

class InvalidInputException (

        val fieldName: String = "",
        message: String = "Invalid Input"
) : RuntimeException(message)