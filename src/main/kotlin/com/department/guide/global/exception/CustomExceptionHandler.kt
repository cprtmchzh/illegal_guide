package com.department.guide.global.exception

import com.department.guide.global.httpStatus.BaseResponse
import com.department.guide.global.httpStatus.ResultCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.MailSendException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleValidationExceptions(ex: MethodArgumentNotValidException):
            ResponseEntity<BaseResponse<Map<String, String>>> {

        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->

            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }

        return ResponseEntity(BaseResponse(

                ResultCode.ERROR.name,
                errors,
                ResultCode.ERROR.msg
        ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidInputException::class)
    protected fun invalidInputException(ex: InvalidInputException):
            ResponseEntity<BaseResponse<Map<String, String>>> {

        val errors = mapOf(ex.fieldName to (ex.message ?: "Not Exception Message"))

        return ResponseEntity(BaseResponse(
                ResultCode.ERROR.name,
                errors,
                ResultCode.ERROR.msg
        ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    protected fun badCredentialsException(ex: BadCredentialsException):
            ResponseEntity<BaseResponse<Map<String, String>>> {

        val errors = mapOf("로그인 실패" to "이메일 또는 패스워드가 일치하지 않습니다.")

        return ResponseEntity(BaseResponse(
                ResultCode.ERROR.name,
                errors,
                ResultCode.ERROR.msg
        ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    protected fun emailSendException(ex: MailSendException):
            ResponseEntity<BaseResponse<Map<String, String>>> {

        val errors = mapOf("이메일 전송 실패" to (ex.message ?: "이메일 전송에 실패하였습니다."))

        return ResponseEntity(BaseResponse(
                ResultCode.ERROR.name,
                errors,
                ResultCode.ERROR.msg
        ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    protected fun defaultException(ex: Exception):
            ResponseEntity<BaseResponse<Map<String, String>>> {

        val errors = mapOf("미처리 에러" to (ex.message ?: "Not Exception Message"))

        return ResponseEntity(BaseResponse(
            ResultCode.ERROR.name,
            errors,
            ResultCode.ERROR.msg
        ), HttpStatus.BAD_REQUEST)
    }
}