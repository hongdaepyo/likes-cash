package com.dphong.likescash.domain.member

import com.dphong.likescash.domain.seller.SellerDeposit
import jakarta.persistence.*

@Table(name = "Sellers")
@Entity
@DiscriminatorValue("SELLER")
class Seller(
    id: Long? = null,
    username: String,
    password: String,
    name: String?
) : Member(id, username, password, name, MemberRole.SELLER) {
    @OneToOne(mappedBy = "seller", cascade = [CascadeType.ALL])
    var deposit: SellerDeposit = SellerDeposit(seller = this)

    fun charge(amount: Long) {
        deposit.charge(amount)
    }
}
