package me.mementomorri.karsten.dto

import java.util.*

data class TestEntityDTO(
    val id: UUID,
    val documentDate: Date? = null,
    val dictionaryValueId: UUID,
    val dictionaryValueName: String? = null,
    val sortOrder: String? =null
) {
    var testName: String = "Test"
}
