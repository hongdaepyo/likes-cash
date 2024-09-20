package com.dphong.likescash.domain.product

import jakarta.persistence.Embeddable
import java.time.Instant

@Embeddable
data class ProductSchedule(
    var startedAt: Instant? = null,
    var endedAt: Instant? = null
) {
    companion object {
        val DISABLED = ProductSchedule(Instant.MAX, Instant.MIN)
    }
}
