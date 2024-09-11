package com.dphong.likescash.common.config.web

import com.dphong.likescash.common.annotation.LoginMember
import com.dphong.likescash.common.config.auth.MemberFacade
import com.dphong.likescash.common.exception.AuthenticationException
import com.dphong.likescash.common.utils.SecurityUtil
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class LoginMemberArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.parameterAnnotations.any { it is LoginMember }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        request: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): MemberFacade {
        return SecurityUtil.getMemberOrNull()
            ?: throw AuthenticationException("로그인한 사용자가 아닙니다.")
    }
}
