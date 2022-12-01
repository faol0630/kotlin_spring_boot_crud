package com.example.demo.dao

import com.example.demo.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDao: JpaRepository<Product, String>
