package com.dphong.likescash.repository.product

import com.dphong.likescash.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<Product, Long>
