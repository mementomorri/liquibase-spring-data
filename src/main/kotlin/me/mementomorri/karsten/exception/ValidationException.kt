package me.mementomorri.karsten.exception

import me.mementomorri.karsten.dto.ItemValidationError

data class ValidationException(val itemValidationErrors: List<ItemValidationError>) : RuntimeException()