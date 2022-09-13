package com.vsharkovski.facilitybookingsystem.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "slots")
data class Slot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @CreationTimestamp
    val creationTime: Timestamp = Timestamp(System.currentTimeMillis()),

    @UpdateTimestamp
    val updateTime: Timestamp = creationTime,

    /* The user who created this slot, no matter its type, if the slot was created manually. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    val creator: User?,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    val type: ESlotType,

    /* The slot scheme this slot is a part of, if it is of type SCHEME. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_scheme_id")
    val slotScheme: SlotScheme?,

    /* The facility this slot is for, if it is of type DEPLOYED. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    val facility: Facility?,

    /* Users currently registered for this slot */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "slot_users",
        joinColumns = [JoinColumn(name = "slot_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")],
    )
    val users: List<User> = emptyList(),

    /* The start time of this slot in seconds. If  relative to the start of day (0). */
    val startTime: Timestamp?,

    /* The end time of this slot in seconds relative to the start of the day (0). */
    val endTime: Timestamp?,

    /* The maximum number of users who can register for this slot. */
    val capacity: Int?
)
