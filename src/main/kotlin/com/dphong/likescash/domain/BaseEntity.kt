package com.dphong.likescash.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity<ID> {

    @Column
    var createdId: String = ""

    @Column
    var updatedId: String = ""

    @CreatedDate
    @Column
    var createdAt: Instant = Instant.MIN
        protected set

    @LastModifiedDate
    @Column
    var updatedAt: Instant = Instant.MIN
        protected set
}
