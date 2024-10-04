package com.dphong.likescash.api.member.service

import com.dphong.likescash.api.member.model.SignUpMemberRequest
import com.dphong.likescash.api.member.model.SignUpMemberResponse
import com.dphong.likescash.api.member.model.SignUpMemberStatus
import com.dphong.likescash.common.response.StatusDataResult
import com.dphong.likescash.domain.member.Member
import com.dphong.likescash.repository.member.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpMemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun signUp(request: SignUpMemberRequest): StatusDataResult<SignUpMemberStatus, SignUpMemberResponse> {
        val member = memberRepository.findByUsernameOrNull(request.username)
        if (member != null) {
            return StatusDataResult(SignUpMemberStatus.USERNAME_ALREADY_EXISTS)
        }

        val encodedPassword = passwordEncoder.encode(request.password)
        val savedMember = memberRepository.save(Member(request.username, encodedPassword, request.name))
        return StatusDataResult(SignUpMemberStatus.SUCCESS, SignUpMemberResponse.of(savedMember))
    }
}
