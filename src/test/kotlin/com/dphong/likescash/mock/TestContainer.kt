package com.dphong.likescash.mock

import com.dphong.likescash.api.posts.service.ReactionPostService
import com.dphong.likescash.api.posts.service.SubmitPostService
import com.dphong.likescash.api.product.service.RegisterProductService
import com.dphong.likescash.repository.SellerDepositOrderRepository
import com.dphong.likescash.repository.member.MemberRepository
import com.dphong.likescash.repository.posts.LikesRepository
import com.dphong.likescash.repository.product.ProductRepository

class TestContainer {

    lateinit var postRepository: FakePostRepository
    lateinit var memberRepository: MemberRepository
    lateinit var productRepository: ProductRepository
    lateinit var likesRepository: LikesRepository
    lateinit var sellerDepositOrderRepository: SellerDepositOrderRepository

    lateinit var registerProductService: RegisterProductService
    lateinit var submitPostService: SubmitPostService
    lateinit var reactionPostService: ReactionPostService

    fun build(): TestContainer {
        memberRepository = FakeMemberRepository()
        productRepository = FakeProductRepository()
        postRepository = FakePostRepository()
        likesRepository = FakeLikesRepository()
        sellerDepositOrderRepository = FakeSellerDepositOrderRepository()

        registerProductService = RegisterProductService(memberRepository, productRepository)
        submitPostService = SubmitPostService(postRepository, productRepository, memberRepository)
        reactionPostService = ReactionPostService(postRepository, memberRepository, likesRepository)
        return this
    }

}
