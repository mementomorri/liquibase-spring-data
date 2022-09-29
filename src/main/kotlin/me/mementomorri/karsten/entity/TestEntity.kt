package me.mementomorri.karsten.entity

import me.mementomorri.karsten.dto.TestEntityDTO
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
data class TestEntity(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: UUID = UUID.randomUUID(),
    val documentDate: Date? = null,
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val dictionaryValueId: UUID = UUID.randomUUID(),
    val dictionaryValueName: String? = null,
    val sortOrder: String? =null
) {
    var testName: String = "Test"

    fun encode(): TestEntityDTO = TestEntityDTO(
        id = id,
        documentDate = documentDate,
        dictionaryValueId = dictionaryValueId,
        dictionaryValueName = dictionaryValueName,
        sortOrder = sortOrder
    )

    companion object {
        fun parse(entity: EntityRequestBody) = TestEntity(
            documentDate = entity.documentDate,
            dictionaryValueName = entity.dictionaryValueName,
            sortOrder = entity.sortOrder
        )
    }

}
