package com.example.demo.service

import com.example.demo.dao.ClientDao
import com.example.demo.domain.Client1
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional


@Transactional
@Service
class ClientServices(val clientDao: ClientDao): BasicCrud<Client1, Int> {

    override fun findAll(): List<Client1> {
        return clientDao.findAll()
    }

    override fun findById(id: Int): Client1? {
        return clientDao.findByIdOrNull(id)
    }

    override fun save(param1: Client1): Client1 {
        return if (!this.clientDao.existsById(param1.id)) {
            this.clientDao.save(param1)
        } else {
            throw DuplicateKeyException("${param1.name} does exists(from save)")
        }
    }

    override fun update(param1: Client1): Client1 {
        return if (this.clientDao.existsById(param1.id)) {
            this.clientDao.save(param1)
        } else {
            throw EntityNotFoundException("${param1.name} does not exists(from update)")
        }
    }

    override fun deleteById(id: Int): Client1 {
        return this.findById(id).apply {
            this@ClientServices.clientDao.deleteById(this!!.id)
        } ?: throw EntityNotFoundException("$id does not exists(from deleteById)")
    }

    override fun deleteAll() = clientDao.deleteAll()

}

//http://localhost:8080/api/v1/client

//para ver desde emulador de androidStudio:
//http://10.0.2.2:8080/api/v1/client