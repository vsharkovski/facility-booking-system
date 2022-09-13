package com.vsharkovski.facilitybookingsystem.repository

import com.vsharkovski.facilitybookingsystem.domain.SlotScheme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SlotSchemeRepository : JpaRepository<SlotScheme, Long> {
    fun findAllByOrderByCreationTimeDesc(): List<SlotScheme>
}