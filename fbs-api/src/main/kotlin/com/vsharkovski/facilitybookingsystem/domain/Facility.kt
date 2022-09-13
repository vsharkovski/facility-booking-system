package com.vsharkovski.facilitybookingsystem.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "facilities")
data class Facility(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @CreationTimestamp
    val creationTime: Timestamp = Timestamp(System.currentTimeMillis()),

    @UpdateTimestamp
    val updateTime: Timestamp = creationTime,

    /* The user who created this slot scheme, if it was created manually. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    val creator: User?,

    /* The name of this facility, to be used for easy identification. */
    @field:NotBlank
    @field:Size(max = 50)
    val name: String,

    /* The location of this facility. */
    @field:NotBlank
    @field:Size(max = 50)
    val location: String,

    /* Information presented when a user attempts to register for a slot for this facility. */
    @field:Size(max = 10000)
    val registrationNote: String?,

    /* The default number of users who can register at one slot for this facility. */
    val defaultSlotCapacity: Int?,

    /* All the slots deployed in this facility. */
    // TODO: Test cascade deletion
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facility", orphanRemoval = true, cascade = [CascadeType.PERSIST])
    val slots: List<Slot> = emptyList(),
)
