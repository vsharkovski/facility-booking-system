package com.vsharkovski.facilitybookingsystem.domain

interface AuthResult

sealed interface AuthLoginResult : AuthResult

data class AuthLoginSuccess(
    val id: Long,
    val username: String,
    val email: String,
    val roles: List<ERole>,
    val jwtCookie: String
) : AuthLoginResult

object AuthLoginFail : AuthLoginResult

sealed interface AuthRegisterResult : AuthResult

object AuthRegisterSuccess : AuthRegisterResult

object AuthUsernameExistsFail : AuthRegisterResult

object AuthEmailExistsFail : AuthRegisterResult

object AuthSavingUserFail : AuthRegisterResult

object AuthInvalidParametersFail : AuthRegisterResult