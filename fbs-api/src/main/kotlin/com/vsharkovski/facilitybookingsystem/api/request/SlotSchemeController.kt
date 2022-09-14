package com.vsharkovski.facilitybookingsystem.api.request

import com.vsharkovski.facilitybookingsystem.service.SlotSchemeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/slot_scheme")
class SlotSchemeController(
    val slotSchemeService: SlotSchemeService
) {
    @GetMapping(value = ["", "/"])
    fun getAllSlotSchemes(): Unit {}
}