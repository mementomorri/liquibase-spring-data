package me.mementomorri.karsten.entity

import org.springframework.validation.annotation.Validated
import java.util.*

@Validated
data class EntityRequestBody(
    val documentDate: Date? = null,
    val dictionaryValueName: String? = null,
    val sortOrder: String? =null
)
