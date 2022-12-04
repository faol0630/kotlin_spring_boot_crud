package com.example.demo.controller

import com.example.demo.domain.Product
import com.example.demo.service.ProductServices
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/product")
class ProductController(productServices: ProductServices): BasicController<Product, Int>(productServices)



