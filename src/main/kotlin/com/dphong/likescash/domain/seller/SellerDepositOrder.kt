package com.dphong.likescash.domain.seller

import com.dphong.likescash.domain.BaseEntity
import com.dphong.likescash.domain.member.Seller
import jakarta.persistence.*
import java.time.Instant

@Table(name = "SellerDepositOrder")
@Entity
class SellerDepositOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId")
    val seller: Seller,
    val amount: Long,
    val orderNumber: String
) : BaseEntity<Long>() {
    val orderedAt: Instant = Instant.now()
    val canceledAt: Instant? = null
    val chargedAt: Instant? = null
    var status: SellerDepositOrderStatus = SellerDepositOrderStatus.ORDERED

}
