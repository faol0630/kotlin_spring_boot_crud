package com.example.demo.domain

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
@Table(name = "Client")
data class Client1(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @get:Size(min = 3, max = 20)
    @NotNull
    val name: String,

    @get:Size(min = 3, max = 20)
    @NotNull
    val managerName: String

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
