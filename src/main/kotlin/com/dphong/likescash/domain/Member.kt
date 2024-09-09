package com.dphong.likescash.domain

import jakarta.persistence.*

@Table(name = "members")
@Entity
class Member(
    val username: String,
    val password: String,
    @Enumerated(EnumType.STRING)
    val role: MemberRole = MemberRole.USER
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var name: String? = null
}
