package com.dphong.likescash.fixtures

import com.dphong.likescash.domain.product.Product

object ProductFixture {
    fun create(
        id: Long? = null
    ) = Product(
        id = id,
        name = "testName",
        seller = MemberFixture.seller()
    )
}
