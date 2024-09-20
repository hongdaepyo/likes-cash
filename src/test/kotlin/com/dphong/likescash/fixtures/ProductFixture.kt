package com.dphong.likescash.fixtures

import com.dphong.likescash.common.extension.minusDays
import com.dphong.likescash.common.extension.plusDays
import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.product.Product
import com.dphong.likescash.domain.product.ProductSchedule
import java.time.Instant

object ProductFixture {
    fun create(
        id: Long? = null
    ) = Product(
        id = id,
        name = "testName",
        seller = MemberFixture.seller()
    )

    fun createVisible(
        id: Long? = null,
        name: String = "testName",
        seller: Member = MemberFixture.seller(),
        schedule: ProductSchedule =  ProductSchedule(Instant.now().minusDays(1), Instant.now().plusDays(1))
    ) = Product(
        id = id,
        name = name,
        seller = seller
    ).apply {
        approval()
        updateSchedule(schedule)
    }
}
