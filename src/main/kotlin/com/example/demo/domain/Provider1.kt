package com.example.demo.domain

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
@Table(name = "Provider")
data class Provider1(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1,

    @NotNull
    @get:Size(min = 3, max = 20)
    val name: String,

    @get:Email
    val email: String
){

    //@ManyToMany
    //val clientsListFromProvider : MutableSet<Client1> = mutableSetOf()

    //@OneToMany
    //val productsListFromProvider : MutableSet<Product> = mutableSetOf()
    //este seria la otra parte de onlyProvider en la entity product


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
