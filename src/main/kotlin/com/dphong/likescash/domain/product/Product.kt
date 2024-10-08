package com.dphong.likescash.domain.product

import com.dphong.likescash.domain.BaseEntity
import com.dphong.likescash.domain.member.Member
import jakarta.persistence.*

@Table(name = "products")
@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId")
    val seller: Member
) : BaseEntity<Long>() {

    companion object {
        fun of(
            name: String,
            seller: Member,
            schedule: ProductSchedule = ProductSchedule.DISABLED
        ): Product =
            Product(null, name, seller).apply {
                this.schedule = schedule
            }
    }

    var isVisible: Boolean = false

    @Embedded
    var schedule: ProductSchedule = ProductSchedule.DISABLED


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    val missions: MutableList<ProductMission> = mutableListOf()

    fun approval() {
        isVisible = true
    }

    fun addMission(mission: ProductMission) {
        missions.add(mission)
    }

    fun updateSchedule(schedule: ProductSchedule) {
        this.schedule = schedule
    }
}
