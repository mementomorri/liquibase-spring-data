package me.mementomorri.karsten.controller

import me.mementomorri.karsten.dto.TestEntityDTO
import me.mementomorri.karsten.entity.EntityRequestBody
import me.mementomorri.karsten.service.TestEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class EntityController(
    @Autowired private  val service: TestEntityService
) {

    companion object {
        const val PARAM_PATH_ID: String = "id"
        const val URI_PATH_ENTITY: String = "/testentity"
        const val URI_PATH_ENTITY_ID: String = "$URI_PATH_ENTITY/{$PARAM_PATH_ID}"
    }

    @GetMapping(URI_PATH_ENTITY)
    @ResponseStatus(HttpStatus.OK)
    fun getAllEntities(@PageableDefault(value = 50)  pageable: Pageable): Page<TestEntityDTO> =
        service.findAll(pageable)

    @GetMapping(URI_PATH_ENTITY_ID)
    @ResponseStatus(HttpStatus.OK)
    fun getEntityById(@PathVariable(PARAM_PATH_ID) id: UUID): Optional<TestEntityDTO> =
        service.findById(id)

    @PostMapping(URI_PATH_ENTITY)
    @ResponseStatus(HttpStatus.CREATED)
    fun postEntity(@RequestBody entity: EntityRequestBody): TestEntityDTO =
        service.save(entity)

    @PutMapping(URI_PATH_ENTITY_ID)
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun putEntity(@PathVariable(PARAM_PATH_ID) id: UUID,
                  @RequestBody entity: EntityRequestBody): TestEntityDTO =
        service.update(id, entity)

    @PatchMapping(URI_PATH_ENTITY_ID)
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun patchEntity(@PathVariable(PARAM_PATH_ID) id: UUID,
                    @RequestBody entity: EntityRequestBody): TestEntityDTO =
        service.patch(id, entity)

    @DeleteMapping(URI_PATH_ENTITY_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEntity(@PathVariable(PARAM_PATH_ID) id: UUID) =
        service.deleteEntity(id)
}