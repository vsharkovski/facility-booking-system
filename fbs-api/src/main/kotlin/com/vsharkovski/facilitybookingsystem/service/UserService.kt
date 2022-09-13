package com.vsharkovski.facilitybookingsystem.service

import com.vsharkovski.facilitybookingsystem.domain.User
import com.vsharkovski.facilitybookingsystem.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {
    fun findUserById(id: Long): User? = userRepository.findByIdOrNull(id)
}