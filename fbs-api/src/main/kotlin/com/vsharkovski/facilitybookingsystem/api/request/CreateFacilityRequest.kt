package com.vsharkovski.facilitybookingsystem.api.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CreateFacilityRequest(
    @field:NotBlank
    @field:Size(max = 50)
    val name: String,

    @field:NotBlank
    @field:Size(max = 50)
    val location: String,

    @field:Size(max = 10000)
    val registrationNote: String?,

    val defaultSlotCapacity: Int?,
)
