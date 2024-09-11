package com.dphong.likescash.api.product

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.common.reponse.DataResult
import com.dphong.likescash.domain.Product
import com.dphong.likescash.fixtures.MemberFixture
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@BaseWebMvcTest([ProductController::class])
class ProductControllerTest(
    private val mockMvc: MockMvc,
    @MockBean private val productGetter: ProductGetter
) {

    @Test
    fun `상품을 조회한다`() {
        // given
        given(productGetter.getProducts()).willReturn(
            DataResult(listOf(Product.of("testProduct1", MemberFixture.seller())))
        )

        // when
        // then
        mockMvc.get("/v1/products")
            .andExpect {
                status { isOk() }
                jsonPath("$.data[0].name") {
                    value("testProduct1")
                }
            }
    }
}
