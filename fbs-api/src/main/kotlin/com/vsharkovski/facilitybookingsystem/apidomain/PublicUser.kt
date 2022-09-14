package com.vsharkovski.facilitybookingsystem.apidomain

data class PublicUser(
    val id: Long,
    val username: String?,
    val roles: List<String>?,
)
