package com.example.demo.dao

import com.example.demo.domain.Client1
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientDao: JpaRepository<Client1, Int>