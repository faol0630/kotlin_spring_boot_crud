package com.example.demo.controller


import com.example.demo.domain.Provider1
import com.example.demo.service.ProviderServices
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/provider")
class ProviderController(providerServices: ProviderServices): BasicController<Provider1, Int>(providerServices)