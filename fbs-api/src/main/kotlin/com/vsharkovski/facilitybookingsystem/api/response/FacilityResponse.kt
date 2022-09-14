package com.vsharkovski.facilitybookingsystem.api.response

import com.vsharkovski.facilitybookingsystem.apidomain.PublicFacility

sealed interface FacilityResponse : Response

data class FacilityMessageResponse(
    val message: String,
    override val success: Boolean
) : FacilityResponse

data class FacilityInfoResponse(
    val facility: PublicFacility,
    override val success: Boolean = true
) : FacilityResponse

data class FacilityInfoListResponse(
    val facilities: List<PublicFacility>,
    override val success: Boolean = true
) : FacilityResponse