package com.example.demo.controller

import com.example.demo.service.BasicCrud
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

abstract class BasicController<T, ID>(private val basicCrud: BasicCrud<T, ID>) {

    @ApiOperation("Get all")
    @GetMapping
    fun findAll() = ResponseEntity.status(HttpStatus.OK).body(basicCrud.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: ID): ResponseEntity<T> {
        val entity = basicCrud.findById(id)
        return ResponseEntity.status(
            if (entity != null) {
                HttpStatus.OK //200
            } else {
                HttpStatus.NO_CONTENT //204
            }
        ).body(entity)
    }

    @PostMapping
    fun save(@RequestBody body: T) = ResponseEntity.status(HttpStatus.CREATED).body(this.basicCrud.save(body))

    @PutMapping
    fun update(@RequestBody body: T) = ResponseEntity.status(HttpStatus.OK).body(this.basicCrud.update(body))

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: ID) = ResponseEntity.status(HttpStatus.OK).body(this.basicCrud.deleteById(id))
}