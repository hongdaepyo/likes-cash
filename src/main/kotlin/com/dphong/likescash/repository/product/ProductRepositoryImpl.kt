package com.dphong.likescash.repository.product

import com.dphong.likescash.domain.product.Product
import com.dphong.likescash.domain.product.QProduct.product
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class ProductRepositoryImpl(
    private val productJpaRepository: ProductJpaRepository,
    private val factory: JPAQueryFactory
) : ProductRepository {

    override fun save(product: Product): Product {
        return productJpaRepository.save(product)
    }

    override fun findAll(): List<Product> =
        factory.selectFrom(product)
            .where(
                product.isVisible.isTrue,
                product.schedule.startedAt.before(Instant.now()),
                product.schedule.endedAt.after(Instant.now())
            )
            .fetch()

    override fun findByIdOrNull(productId: Long): Product? {
        return productJpaRepository.findByIdOrNull(productId)
    }
}
