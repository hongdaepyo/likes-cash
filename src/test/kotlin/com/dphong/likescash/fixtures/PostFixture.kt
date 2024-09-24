package com.dphong.likescash.fixtures

import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.posts.Post
import com.dphong.likescash.domain.posts.PostPlatform
import com.dphong.likescash.domain.product.Product

object PostFixture {
    fun create(
        id : Long? = null,
        url: String = "www.test.com",
        platform: PostPlatform = PostPlatform.NAVER,
        member: Member = MemberFixture.user(),
        product: Product = ProductFixture.create()
    ) = Post(id, url, platform, member, product)
}
