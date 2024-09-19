package com.dphong.likescash.domain.posts

import jakarta.persistence.Embeddable

@Embeddable
data class PostReaction(var likesCount: Int = 0)
