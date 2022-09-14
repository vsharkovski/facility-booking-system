package com.vsharkovski.facilitybookingsystem.api.response

import com.vsharkovski.facilitybookingsystem.apidomain.PublicSlotScheme

sealed interface SlotSchemeResponse : Response

data class SlotSchemeMessageResponse(
    val message: String,
    override val success: Boolean
) : SlotSchemeResponse

data class SlotSchemeInfoResponse(
    val facility: PublicSlotScheme,
    override val success: Boolean = true
) : SlotSchemeResponse

data class SlotSchemeInfoListResponse(
    val facilities: List<PublicSlotScheme>,
    override val success: Boolean = true
) : SlotSchemeResponse