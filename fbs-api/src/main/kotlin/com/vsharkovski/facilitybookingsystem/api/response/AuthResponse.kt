package com.vsharkovski.facilitybookingsystem.api.response

sealed interface AuthResponse : Response

data class AuthUserInfoResponse(
    val id: Long,
    val username: String,
    val roles: List<String>
) : AuthResponse {
    override val success = true
}

data class AuthMessageResponse(override val success: Boolean, val message: String) : AuthResponse