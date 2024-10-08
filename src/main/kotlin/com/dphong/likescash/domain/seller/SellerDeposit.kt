package com.dphong.likescash.domain.seller

import com.dphong.likescash.domain.BaseEntity
import com.dphong.likescash.domain.member.Member
import jakarta.persistence.*

@Table(name = "SellerDeposit")
@Entity
class SellerDeposit(
    @Id
    val id: Long? = null,
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId")
    val seller: Member,
): BaseEntity<Long>() {
    var amount: Long = 0

    fun charge(amount: Long) {
        this.amount += amount
    }

    fun subtract(amount: Long) {
        require (this.amount > amount) { "잔액이 부족합니다." }
        this.amount -= amount
    }
}
