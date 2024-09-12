package com.dphong.likescash.domain.posts

import com.dphong.likescash.domain.BaseEntity
import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.domain.product.Product
import jakarta.persistence.*
import java.time.Instant

@Table(name = "posts")
@Entity
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var linkUrl: String,
    @Enumerated(EnumType.STRING)
    var platform: PostPlatform,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    val member: Member,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    val product: Product
) : BaseEntity<Long>() {

    val submittedAt: Instant
        get() = createdAt
}
