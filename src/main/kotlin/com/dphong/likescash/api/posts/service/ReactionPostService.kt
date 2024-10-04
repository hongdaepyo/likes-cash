package com.dphong.likescash.api.posts.service

import com.dphong.likescash.api.posts.model.ReactionPostResponse
import com.dphong.likescash.api.posts.model.ReactionPostStatus
import com.dphong.likescash.common.response.StatusDataResult
import com.dphong.likescash.domain.posts.Post
import com.dphong.likescash.repository.member.MemberRepository
import com.dphong.likescash.repository.posts.LikesRepository
import com.dphong.likescash.repository.posts.PostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ReactionPostService(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository,
    private val likesRepository: LikesRepository
) {

    @Transactional
    fun likes(memberId: Long, postId: Long): StatusDataResult<ReactionPostStatus, ReactionPostResponse> {
        val member = memberRepository.findByIdOrNull(memberId)
            ?: return StatusDataResult(ReactionPostStatus.MEMBER_NOT_FOUND)
        val post: Post = postRepository.findByIdOrNull(postId)
            ?: return StatusDataResult(ReactionPostStatus.POST_NOT_FOUND)

        likesRepository.findByMemberIdAndPostId(member.id!!, post.id!!)?.let {
            return StatusDataResult(ReactionPostStatus.ALREADY_LIKED, ReactionPostResponse.from(post.reaction))
        }
        likesRepository.save(member.likes(post))
        post.addLikes()
        return StatusDataResult(ReactionPostStatus.SUCCESS, ReactionPostResponse.from(post.reaction))
    }

    @Transactional
    fun cancelLikes(memberId: Long, postId: Long): StatusDataResult<ReactionPostStatus, ReactionPostResponse> {
        val member = memberRepository.findByIdOrNull(memberId)
            ?: return StatusDataResult(ReactionPostStatus.MEMBER_NOT_FOUND)
        val post: Post = postRepository.findByIdOrNull(postId)
            ?: return StatusDataResult(ReactionPostStatus.POST_NOT_FOUND)
        likesRepository.findByMemberIdAndPostId(member.id!!, post.id!!)?.let { likes ->
            likesRepository.delete(likes)
            post.removeLikes()
        } ?: return StatusDataResult(ReactionPostStatus.LIKES_NOT_FOUND, ReactionPostResponse.from(post.reaction))

        return StatusDataResult(ReactionPostStatus.SUCCESS, ReactionPostResponse.from(post.reaction))
    }
}
