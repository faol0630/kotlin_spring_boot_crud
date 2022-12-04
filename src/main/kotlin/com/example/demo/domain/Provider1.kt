package com.example.demo.domain

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size


@Entity
@Table(name = "Provider")
data class Provider1(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @get:Size(min = 3, max = 20)
    val name: String,

    @get:Email
    val email: String
){
    override fun equals(other: Any?): Boolean {
        other?: return false
        if (other === this){
            return true
        }
        if (this.javaClass != other.javaClass){
            return false
        }
        other as Provider1

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
