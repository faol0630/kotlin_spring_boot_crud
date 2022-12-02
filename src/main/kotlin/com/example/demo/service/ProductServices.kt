package com.example.demo.service


import com.example.demo.dao.ProductDao
import com.example.demo.domain.Product
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Transactional
@Service
class ProductServices(val productDao: ProductDao) : BasicCrud<Product, String> {


    override fun findAll(): List<Product> {
        return productDao.findAll()
    }

    override fun findById(id: String): Product? {
        return this.productDao.findByIdOrNull(id)
    }


    override fun save(param1: Product): Product {
        return if (!this.productDao.existsById(param1.name)) {
            this.productDao.save(param1)
        } else {
            throw DuplicateKeyException("${param1.name} does exists(from save)")
        }
    }

    override fun update(param1: Product): Product {
        return if (this.productDao.existsById(param1.name)) {
            this.productDao.save(param1)
        } else {
            throw EntityNotFoundException("${param1.name} does not exists(from update)")
        }
    }

    override fun deleteById(id: String): Product {
        return this.findById(id).apply {
            this@ProductServices.productDao.deleteById(this!!.name)
        } ?: throw EntityNotFoundException("$id does not exists(from deleteById)")
    }

    override fun deleteAll() = productDao.deleteAll()




}

//http://localhost:8080/api/v1/product