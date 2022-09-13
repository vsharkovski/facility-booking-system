package com.vsharkovski.facilitybookingsystem.domain.result

import com.vsharkovski.facilitybookingsystem.domain.Facility

sealed interface FacilityResult

data class FacilityCreated(val facility: Facility) : FacilityResult

object FacilityMissingUserFail : FacilityResult

object FacilityDatabaseSavingFail : FacilityResult