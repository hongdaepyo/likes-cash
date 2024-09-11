package com.dphong.likescash.common.utils

import com.dphong.likescash.common.config.auth.MemberAuthenticationToken
import com.dphong.likescash.common.config.auth.MemberDetails
import com.dphong.likescash.common.config.auth.MemberFacade
import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtil {

    fun getMemberOrNull(): MemberFacade? {
        val token = SecurityContextHolder.getContext().authentication as? MemberAuthenticationToken
            ?: return null
        val memberDetails = token.principal as MemberDetails
        return MemberFacade(memberDetails)
    }

    fun getMemberId(): Long? = getMemberOrNull()?.memberId
}
