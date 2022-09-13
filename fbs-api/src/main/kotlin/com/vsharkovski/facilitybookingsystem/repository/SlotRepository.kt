package com.vsharkovski.facilitybookingsystem.repository

import com.vsharkovski.facilitybookingsystem.domain.Slot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SlotRepository : JpaRepository<Slot, Long> {
}