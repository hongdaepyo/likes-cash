package com.dphong.likescash.mock

import com.dphong.likescash.api.posts.service.SubmitPostService
import com.dphong.likescash.api.product.service.RegisterProductService
import com.dphong.likescash.repository.member.MemberRepository
import com.dphong.likescash.repository.product.ProductRepository

class TestContainer {

    lateinit var postRepository: FakePostRepository
    lateinit var memberRepository: MemberRepository
    lateinit var productRepository: ProductRepository

    lateinit var registerProductService: RegisterProductService
    lateinit var submitPostService: SubmitPostService

    fun build(): TestContainer {
        memberRepository = FakeMemberRepository()
        productRepository = FakeProductRepository()
        postRepository = FakePostRepository()
        registerProductService = RegisterProductService(memberRepository, productRepository)
        submitPostService = SubmitPostService(postRepository, productRepository, memberRepository)
        return this
    }

}
