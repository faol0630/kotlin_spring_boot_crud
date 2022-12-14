package com.example.demo.domain

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "Product")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @get:Size(min = 3, max = 20)
    @NotNull
    val name: String,

    @get:Min(0)
    val price: Double? = 55.5,

    @get:Min(0)
    val stock: Int = 0,

    @ManyToOne
    val onlyProvider: Provider1

) {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (other === this) {
            return true
        }
        if (this.javaClass != other.javaClass) {
            return false
        }
        other as Product

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
