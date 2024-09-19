package com.dphong.likescash.api.posts.service

import com.dphong.likescash.api.posts.model.SubmitPostRequest
import com.dphong.likescash.api.posts.model.SubmitPostResponse
import com.dphong.likescash.api.posts.model.SubmitPostStatus
import com.dphong.likescash.common.reponse.StatusDataResult
import com.dphong.likescash.domain.posts.Post
import com.dphong.likescash.repository.member.MemberRepository
import com.dphong.likescash.repository.posts.PostRepository
import com.dphong.likescash.repository.product.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubmitPostService(
    private val postRepository: PostRepository,
    private val productRepository: ProductRepository,
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun submit(memberId: Long, request: SubmitPostRequest): StatusDataResult<SubmitPostStatus, SubmitPostResponse> {
        val member = memberRepository.findByIdOrNull(memberId)
            ?: return StatusDataResult(SubmitPostStatus.MEMBER_NOT_FOUND)
        val product = productRepository.findByIdOrNull(request.productId)
            ?: return StatusDataResult(SubmitPostStatus.PRODUCT_NOT_FOUND)
        // TODO: linkUrl 중복체크
        val post = postRepository.save(
            Post(
                member = member,
                product = product,
                linkUrl = request.linkUrl,
                platform = request.platform
            )
        )
        return StatusDataResult(SubmitPostStatus.SUCCESS, SubmitPostResponse(post.id!!))
    }
}
