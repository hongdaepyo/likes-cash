package com.dphong.likescash.api.product

import com.dphong.likescash.common.reponse.DataResult
import com.dphong.likescash.domain.Product
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/products")
@RestController
class ProductController(
    private val productGetter: ProductGetter
) {

    @GetMapping
    fun getProducts(): ResponseEntity<DataResult<List<Product>>> =
        productGetter.getProducts().toResponseEntity()
}
