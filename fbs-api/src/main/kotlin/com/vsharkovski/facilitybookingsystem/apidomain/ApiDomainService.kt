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

    final val roleEnumToString: Map<ERole, String> =
        roleStringToEnum.entries.associate { it.value to it.key }

    final val slotTypeStringToEnum: Map<String, ESlotType> =
        mapOf("SCHEME" to ESlotType.SCHEME, "DEPLOYED" to ESlotType.DEPLOYED)

    final val slotTypeEnumToString: Map<ESlotType, String> =
        slotTypeStringToEnum.entries.associate { it.value to it.key }

    final

    fun userToPublic(
        user: User,
        includeUsername: Boolean = false,
        includeRoles: Boolean = false,
    ): PublicUser = PublicUser(
        id = user.id,
        username = if (includeUsername) user.username else null,
        roles = if (includeRoles) user.roles.mapNotNull { roleEnumToString[it.name] } else null,
    )

    fun facilityToPublic(
        facility: Facility
    ): PublicFacility = PublicFacility(
        id = facility.id,
        creationTime = facility.creationTime,
        updateTime = facility.updateTime,
        creator = facility.creator?.let { userToPublic(it) },
        name = facility.name,
        location = facility.location,
        registrationNote = facility.registrationNote,
        defaultSlotCapacity = facility.defaultSlotCapacity,
        slots = facility.slots.map { slotToPublic(it) }
    )

    fun slotToPublic(
        slot: Slot
    ): PublicSlot = PublicSlot(
        id = slot.id,
        type = slotTypeEnumToString[slot.type]!!,
        startTime = slot.startTime,
        endTime = slot.endTime,
        capacity = slot.capacity,
        users = slot.users.map { userToPublic(it, includeUsername = false, includeRoles = false) }
    )
}