package com.dphong.likescash.api.product

import com.dphong.likescash.api.product.model.RegisterProductRequest
import com.dphong.likescash.api.product.model.RegisterProductResponse
import com.dphong.likescash.api.product.model.RegisterProductStatus
import com.dphong.likescash.common.config.auth.SecurityConfig
import com.dphong.likescash.common.reponse.StatusDataResult
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Import(SecurityConfig::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@WebMvcTest(RegisterProductController::class)
class RegisterProductControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    @MockBean private val registerProductService: RegisterProductService
) {

    @WithMockUser
    @Test
    fun `상품을 등록한다`() {
        // given
        val request = RegisterProductRequest("네이버 블로그에 식당 홍보글 작성하기")
        val result = StatusDataResult(RegisterProductStatus.SUCCESS, RegisterProductResponse(1, request.name))
        given(registerProductService.register(1, request)).willReturn(result)

        // when
        // then
        mockMvc.perform(
            post("/v1/sellers/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect {
            status().isOk
            jsonPath("$.data.name").value(request.name)
        }
    }
}
