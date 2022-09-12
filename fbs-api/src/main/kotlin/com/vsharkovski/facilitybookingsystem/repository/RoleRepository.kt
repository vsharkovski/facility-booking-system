package com.vsharkovski.facilitybookingsystem.repository

import com.vsharkovski.facilitybookingsystem.domain.ERole
import com.vsharkovski.facilitybookingsystem.domain.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: ERole): Role?
}