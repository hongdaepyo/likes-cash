package com.dphong.likescash.api.seller

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.api.seller.model.SellerDepositRequest
import com.dphong.likescash.api.seller.model.SellerDepositResponse
import com.dphong.likescash.common.response.CommonStatus
import com.dphong.likescash.common.response.StatusDataResult
import com.dphong.likescash.mock.FakeAuthentication
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.put

@BaseWebMvcTest([SellerDepositController::class])
class SellerDepositControllerTest(
    private val mockMvc: MockMvc,
    @MockBean private val sellerDepositService: SellerDepositService,
    private val objectMapper: ObjectMapper
) {

    @Test
    fun `예치금을 충전한다`() {
        // given
        SecurityContextHolder.getContext().authentication = FakeAuthentication.MEMBER.authentication
        val request = SellerDepositRequest("testOrderNumber", 1000)
        given(sellerDepositService.charge(any(), any())).willReturn(
            StatusDataResult(CommonStatus.SUCCESS, SellerDepositResponse(1, 1000))
        )

        // when
        // then
        mockMvc.put("/v1/sellers/deposit") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isOk() }
            jsonPath("$.status") { value("SUCCESS") }
            jsonPath("$.data.amount") { value(1000) }
        }
    }
}
