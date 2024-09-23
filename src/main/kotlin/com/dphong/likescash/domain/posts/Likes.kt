package com.dphong.likescash.domain.posts

import com.dphong.likescash.domain.BaseEntity
import jakarta.persistence.*

@Table(name = "Likes")
@Entity
class Likes(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val memberId: Long,
    val postId: Long,
): BaseEntity<Long>()
