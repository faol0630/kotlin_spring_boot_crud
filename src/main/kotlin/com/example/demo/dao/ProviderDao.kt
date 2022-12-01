package com.example.demo.dao

import com.example.demo.domain.Provider1
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProviderDao: JpaRepository<Provider1, Int>

