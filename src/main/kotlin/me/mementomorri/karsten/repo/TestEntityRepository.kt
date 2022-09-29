package me.mementomorri.karsten.repo

import me.mementomorri.karsten.entity.TestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TestEntityRepository: JpaRepository<TestEntity, UUID>