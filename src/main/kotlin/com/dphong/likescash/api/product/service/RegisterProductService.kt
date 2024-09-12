package com.dphong.likescash.api.product.service

import com.dphong.likescash.api.product.model.RegisterProductRequest
import com.dphong.likescash.api.product.model.RegisterProductResponse
import com.dphong.likescash.api.product.model.RegisterProductStatus
import com.dphong.likescash.common.reponse.StatusDataResult
import com.dphong.likescash.domain.member.MemberRole
import com.dphong.likescash.repository.member.MemberRepository
import com.dphong.likescash.repository.product.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterProductService(
    private val memberRepository: MemberRepository,
    private val productRepository: ProductRepository
) {
    @Transactional
    fun register(sellerId: Long, request: RegisterProductRequest): StatusDataResult<RegisterProductStatus, RegisterProductResponse> {
        val seller = memberRepository.findByIdAndRole(sellerId, MemberRole.SELLER)
            ?: return StatusDataResult(RegisterProductStatus.SELLER_NOT_FOUND)

        val product = productRepository.save(request.toProduct(seller))
        return StatusDataResult(
            RegisterProductStatus.SUCCESS,
            RegisterProductResponse(product.id!!, product.name)
        )
    }
}
