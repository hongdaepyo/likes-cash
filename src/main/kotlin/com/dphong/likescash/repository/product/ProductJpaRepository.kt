package com.dphong.likescash.repository.product

import com.dphong.likescash.domain.product.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<Product, Long>
