package me.mementomorri.karsten.controller.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import me.mementomorri.karsten.dto.ItemValidationError
import me.mementomorri.karsten.exception.ValidationException


@ControllerAdvice
@RestController
class ValidationExceptionAdviceConfig : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ValidationException::class)
    fun handleClientException(validationException: ValidationException, request: WebRequest) =
            ResponseEntity<Collection<ItemValidationError>>(validationException.itemValidationErrors, HttpStatus.PRECONDITION_FAILED)
}