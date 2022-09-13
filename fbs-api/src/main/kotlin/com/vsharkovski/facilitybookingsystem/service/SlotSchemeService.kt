package com.vsharkovski.facilitybookingsystem.service

import com.vsharkovski.facilitybookingsystem.domain.SlotScheme
import com.vsharkovski.facilitybookingsystem.repository.SlotSchemeRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SlotSchemeService(
    val slotSchemeRepository: SlotSchemeRepository
) {
    private val logger = LoggerFactory.getLogger(FacilityService::class.java)

    fun findSlotSchemeById(id: Long): SlotScheme? = slotSchemeRepository.findByIdOrNull(id)

    fun findAllSlotSchemes(): List<SlotScheme> = slotSchemeRepository.findAllByOrderByCreationTimeDesc()

    // TODO: Creation and updating
}