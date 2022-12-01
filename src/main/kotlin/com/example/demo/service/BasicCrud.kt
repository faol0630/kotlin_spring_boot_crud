package com.example.demo.service


interface BasicCrud<T, ID> {

    fun findAll(): List<T>

    fun findById(id: ID): T?

    fun save(param1: T): T

    fun update(param1: T): T

    fun deleteById(id: ID): T

}