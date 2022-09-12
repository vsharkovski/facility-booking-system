package com.vsharkovski.facilitybookingsystem.apidomain

import com.vsharkovski.facilitybookingsystem.domain.*
import org.springframework.stereotype.Service

@Service
class ApiDomainService {
    final val roleStringToEnum: Map<String, ERole> =
        mapOf(
            "ROLE_ADMIN" to ERole.ROLE_ADMIN,
            "ROLE_MODERATOR" to ERole.ROLE_MODERATOR,
            "ROLE_USER" to ERole.ROLE_USER
        )

    final val roleEnumToString: Map<ERole, String> = roleStringToEnum.entries.associate { it.value to it.key }

    fun userToPublic(
        user: User,
        includeRoles: Boolean = false,
    ): PublicUser = PublicUser(
        id = user.id,
        username = user.username,
        roles = if (includeRoles) user.roles.mapNotNull { roleEnumToString[it.name] } else null,
    )
}