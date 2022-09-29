package me.mementomorri.karsten.service

import me.mementomorri.karsten.dto.TestEntityDTO
import me.mementomorri.karsten.entity.EntityRequestBody
import me.mementomorri.karsten.entity.TestEntity
import me.mementomorri.karsten.exception.DuplicateException
import me.mementomorri.karsten.exception.ResourceNotFoundException
import me.mementomorri.karsten.repo.TestEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class TestEntityService internal constructor(
    @Autowired val testEntityRepository: TestEntityRepository) {

    fun findById(id: UUID) : Optional<TestEntityDTO> = testEntityRepository.findById(id).map{ x -> x.encode() }

    fun findAll(pageable: Pageable) : Page<TestEntityDTO> = testEntityRepository.findAll(pageable).map { x -> x.encode() }

    @Transactional
    fun save(entity: EntityRequestBody): TestEntityDTO {
        try {
            return testEntityRepository.save(TestEntity.parse(entity)).encode()
        } catch (e: DataIntegrityViolationException) {
            throw DuplicateException("This entity already exists. $e")
        }
    }

    @Transactional
    fun deleteEntity(id: UUID) = if (testEntityRepository.existsById(id)) testEntityRepository.deleteById(id) else throw ResourceNotFoundException("This entity been wiped already")

    fun update(id: UUID, entity: EntityRequestBody): TestEntityDTO {
        testEntityRepository.findById(id).orElseThrow { ResourceNotFoundException("Entity:$id - not found") }
        try {
            return testEntityRepository.save(TestEntity.parse(entity)).encode()
        } catch (e: DataIntegrityViolationException){
            throw DuplicateException("Data integrity exception. $e")
        }
    }

    fun patch(id: UUID, entity: EntityRequestBody): TestEntityDTO {
        testEntityRepository.findById(id).orElseThrow { ResourceNotFoundException("Entity:$id - not found") }
        try {
            return testEntityRepository.save(TestEntity.parse(entity)).encode()
        } catch (e: DataIntegrityViolationException){
            throw DuplicateException("Data integrity exception. $e")
        }
    }
}