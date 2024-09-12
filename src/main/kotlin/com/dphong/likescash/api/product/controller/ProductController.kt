package com.dphong.likescash.api.product.controller

import com.dphong.likescash.api.product.model.ProductDetails
import com.dphong.likescash.api.product.service.ProductGetter
import com.dphong.likescash.common.reponse.DataResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/products")
@RestController
class ProductController(
    private val productGetter: ProductGetter
) {

    @GetMapping
    fun getProducts(): ResponseEntity<DataResult<List<ProductDetails>>> =
        productGetter.getProducts().toResponseEntity()

    @GetMapping("/{productId}")
    fun getProductDetails(@PathVariable productId: Long): ResponseEntity<DataResult<ProductDetails>> =
        productGetter.getProductDetails(productId).toResponseEntity()
}
