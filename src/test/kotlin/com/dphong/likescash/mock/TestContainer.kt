package com.dphong.likescash.mock

import com.dphong.likescash.api.product.service.RegisterProductService
import com.dphong.likescash.repository.member.MemberRepository
import com.dphong.likescash.repository.product.ProductRepository

class TestContainer {

    lateinit var memberRepository: MemberRepository
    lateinit var productRepository: ProductRepository

    lateinit var registerProductService: RegisterProductService

    fun build(): TestContainer {
        memberRepository = FakeMemberRepository()
        productRepository = FakeProductRepository()
        registerProductService = RegisterProductService(memberRepository, productRepository)
        return this
    }

}
