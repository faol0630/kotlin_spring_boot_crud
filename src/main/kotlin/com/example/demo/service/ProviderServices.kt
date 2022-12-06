package com.example.demo.service


import com.example.demo.dao.ProviderDao
import com.example.demo.domain.Provider1
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional


@Transactional
@Service
class ProviderServices(val providerDao: ProviderDao): BasicCrud<Provider1, Int> {

    override fun findAll(): List<Provider1> {
        return this.providerDao.findAll()
    }

    override fun findById(id: Int): Provider1? {
        return this.providerDao.findByIdOrNull(id)
    }

    override fun save(param1: Provider1): Provider1 {
        return if (!this.providerDao.existsById(param1.id)) {
            this.providerDao.save(param1)
        }else{
            throw DuplicateKeyException("${param1.name} does exists(from save)")
        }
    }

    override fun update(param1: Provider1): Provider1 {
        return if (this.providerDao.existsById(param1.id)) {
            this.providerDao.save(param1)
        }else{
            throw EntityNotFoundException("${param1.name} does not exists(from update)")
        }
    }

    override fun deleteById(id: Int): Provider1 {
        return this.findById(id).apply {
            this@ProviderServices.providerDao.deleteById(this!!.id)
        } ?: throw EntityNotFoundException("$id does not exists(from deleteById)")
    }

    override fun deleteAll() = providerDao.deleteAll()

}

//http://localhost:8080/api/v1/provider

//from android studio emulator:
//http://10.0.2.2:8080/api/v1/provider