package com.vsharkovski.facilitybookingsystem.service

import com.vsharkovski.facilitybookingsystem.domain.Facility
import com.vsharkovski.facilitybookingsystem.domain.result.FacilityCreated
import com.vsharkovski.facilitybookingsystem.domain.result.FacilityDatabaseSavingFail
import com.vsharkovski.facilitybookingsystem.domain.result.FacilityMissingUserFail
import com.vsharkovski.facilitybookingsystem.domain.result.FacilityResult
import com.vsharkovski.facilitybookingsystem.repository.FacilityRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class FacilityService(
    val facilityRepository: FacilityRepository,
    val userService: UserService
) {
    private val logger = LoggerFactory.getLogger(FacilityService::class.java)

    fun findFacilityById(id: Long): Facility? = facilityRepository.findByIdOrNull(id)

    fun findAllFacilities(): List<Facility> = facilityRepository.findAllByOrderByName()

    @Transactional
    fun createFacility(
        creatorId: Long?,
        name: String,
        location: String,
        registrationNote: String?,
        defaultSlotCapacity: Int?
    ): FacilityResult {
        val creator = creatorId?.let {
            userService.findUserById(it) ?: return FacilityMissingUserFail
        }
        val facility = Facility(
            creator = creator,
            name = name,
            location = location,
            registrationNote = registrationNote,
            defaultSlotCapacity = defaultSlotCapacity
        )
        return try {
            FacilityCreated(facilityRepository.save(facility))
        } catch (e: Exception) {
            logger.error("Error in saving facility [{}]", e.message, e)
            FacilityDatabaseSavingFail
        }
    }

    // TODO: Updating
}