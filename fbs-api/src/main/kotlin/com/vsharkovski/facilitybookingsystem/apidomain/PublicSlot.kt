package com.vsharkovski.facilitybookingsystem.apidomain

import java.sql.Timestamp

data class PublicSlot(
    val id: Long,
    val type: String,
    val startTime: Timestamp?,
    val endTime: Timestamp?,
    val capacity: Int?,
    val users: List<PublicUser>?
)