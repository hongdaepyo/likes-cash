package com.dphong.likescash.api.product

import com.dphong.likescash.BaseWebMvcTest
import com.dphong.likescash.api.product.model.ProductDetails
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
    fun `상품을 목록을 조회한다`() {
        // given
        val product = Product(1L, "testProduct1", MemberFixture.seller())
        given(productGetter.getProducts()).willReturn(
            DataResult(listOf(ProductDetails.from(product)))
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

    @Test
    fun `상품을 한개를 조회한다`() {
        // given
        val product = Product(1L, "testProduct1", MemberFixture.seller())
        given(productGetter.getProductDetails(1L)).willReturn(
            DataResult(ProductDetails.from(product))
        )

        // when
        // then
        mockMvc.get("/v1/products/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.data.name") {
                    value("testProduct1")
                }
            }
    }
}
