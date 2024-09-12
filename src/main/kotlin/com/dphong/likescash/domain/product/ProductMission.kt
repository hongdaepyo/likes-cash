package com.dphong.likescash.domain.product

import com.dphong.likescash.domain.BaseEntity
import jakarta.persistence.*

@Table(name = "productMissions")
@Entity
class ProductMission(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    var product: Product
) : BaseEntity<Long>() {

    var topic: String? = null

    fun updateTopic(topic: String) {
        this.topic = topic
    }
}
