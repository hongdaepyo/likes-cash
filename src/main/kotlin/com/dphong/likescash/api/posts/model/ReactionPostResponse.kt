package com.dphong.likescash.api.posts.model

import com.dphong.likescash.domain.posts.PostReaction

data class ReactionPostResponse(val likesCount: Int) {
    companion object {
        fun from(reaction: PostReaction): ReactionPostResponse {
            return ReactionPostResponse(reaction.likesCount)
        }
    }
}
