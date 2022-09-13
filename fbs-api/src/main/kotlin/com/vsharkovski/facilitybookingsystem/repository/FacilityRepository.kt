package com.vsharkovski.facilitybookingsystem.repository

import com.vsharkovski.facilitybookingsystem.domain.Facility
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FacilityRepository : JpaRepository<Facility, Long> {

}