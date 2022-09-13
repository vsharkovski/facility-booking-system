package com.vsharkovski.facilitybookingsystem.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "slot_schemes")
data class SlotScheme(
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

    /* Name of this slot scheme. */
    @field:NotBlank
    @field:Size(max = 50)
    val name: String,

    /* All the slots in this slot scheme. */
    // TODO: Test cascade deletion
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "slotScheme", orphanRemoval = true, cascade = [CascadeType.PERSIST])
    val slots: List<Slot> = emptyList(),
)
