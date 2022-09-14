package com.vsharkovski.facilitybookingsystem.api

import com.vsharkovski.facilitybookingsystem.api.request.CreateFacilityRequest
import com.vsharkovski.facilitybookingsystem.api.response.FacilityInfoListResponse
import com.vsharkovski.facilitybookingsystem.api.response.FacilityInfoResponse
import com.vsharkovski.facilitybookingsystem.api.response.FacilityMessageResponse
import com.vsharkovski.facilitybookingsystem.api.response.FacilityResponse
import com.vsharkovski.facilitybookingsystem.apidomain.ApiDomainService
import com.vsharkovski.facilitybookingsystem.domain.result.FacilityCreated
import com.vsharkovski.facilitybookingsystem.domain.result.FacilityDatabaseSavingFail
import com.vsharkovski.facilitybookingsystem.domain.result.FacilityMissingUserFail
import com.vsharkovski.facilitybookingsystem.security.service.AuthService
import com.vsharkovski.facilitybookingsystem.service.FacilityService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/facility")
class FacilityController(
    val authService: AuthService,
    val apiDomainService: ApiDomainService,
    val facilityService: FacilityService
) {
    @GetMapping(value = ["", "/"])
    fun getAllFacilities(): ResponseEntity<FacilityResponse> =
        ResponseEntity.ok(
            FacilityInfoListResponse(
                facilityService.findAllFacilities().map { apiDomainService.facilityToPublic(it) })
        )

    @GetMapping("/{id}")
    fun getFacilityById(@PathVariable id: Long): ResponseEntity<FacilityResponse> =
        facilityService.findFacilityById(id)
            ?.let { ResponseEntity.ok(FacilityInfoResponse(apiDomainService.facilityToPublic(it))) }
            ?: ResponseEntity.badRequest().body(FacilityMessageResponse("Invalid id", false))

    @PostMapping(value = ["", "/"])
    @PreAuthorize("hasRole('ADMIN')")
    fun createFacility(@Valid @RequestBody request: CreateFacilityRequest): ResponseEntity<FacilityResponse> {
        val result = facilityService.createFacility(
            creatorId = authService.getAuthenticatedUserId()!!,
            name = request.name,
            location = request.location,
            registrationNote = request.registrationNote,
            defaultSlotCapacity = request.defaultSlotCapacity
        )
        return when (result) {
            is FacilityCreated ->
                ResponseEntity.ok(FacilityInfoResponse(apiDomainService.facilityToPublic(result.facility)))
            is FacilityMissingUserFail, FacilityDatabaseSavingFail ->
                ResponseEntity.internalServerError()
                    .body(FacilityMessageResponse("Internal server error", false))
        }
    }

}