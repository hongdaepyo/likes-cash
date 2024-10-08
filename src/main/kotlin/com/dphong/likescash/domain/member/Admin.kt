package com.dphong.likescash.domain.member

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "Admins")
@Entity
@DiscriminatorValue("ADMIN")
class Admin(
    id: Long? = null,
    username: String,
    password: String,
    name: String?
) : Member(id, username, password, name, MemberRole.ADMIN)
