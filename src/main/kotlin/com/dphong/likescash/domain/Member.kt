package com.dphong.likescash.domain

import jakarta.persistence.*

@Table(name = "members")
@Entity
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
