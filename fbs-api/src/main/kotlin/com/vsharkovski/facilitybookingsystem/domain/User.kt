package com.vsharkovski.facilitybookingsystem.domain

import java.sql.Timestamp
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val creationTime: Timestamp = Timestamp(System.currentTimeMillis()),

    @field:NotBlank
    @field:Size(max = 20)
    val username: String,

    @field:NotBlank
    @field:Size(max = 120)
    val password: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: Set<Role>,

    @field:NotBlank
    @field:Size(max = 50)
    val email: String
) {
    override fun toString(): String = "User(id=$id, username=$username, roles=$roles)"
}