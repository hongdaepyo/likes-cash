package com.dphong.likescash.domain.member

import com.dphong.likescash.domain.BaseEntity
import jakarta.persistence.*

@DiscriminatorColumn(name = "memberType")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Members")
@Entity
abstract class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String,
    val password: String,
    var name: String? = null,
    @Enumerated(EnumType.STRING)
    val role: MemberRole = MemberRole.USER,
) : BaseEntity<Long>() {
    constructor(username: String, password: String, name: String) : this(0, username, password, name, MemberRole.USER)

}
