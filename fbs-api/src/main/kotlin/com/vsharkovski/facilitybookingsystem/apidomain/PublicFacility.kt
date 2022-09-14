package com.vsharkovski.facilitybookingsystem.apidomain

import java.sql.Timestamp

data class PublicFacility(
    val id: Long,
    val creationTime: Timestamp,
    val updateTime: Timestamp,
    val creator: PublicUser?,
    val name: String,
    val location: String,
    val registrationNote: String?,
    val defaultSlotCapacity: Int?,
    val slots: List<PublicSlot>?
)
