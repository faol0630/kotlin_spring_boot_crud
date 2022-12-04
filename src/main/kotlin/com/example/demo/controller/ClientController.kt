package com.example.demo.controller

import com.example.demo.domain.Client1
import com.example.demo.service.ClientServices
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/client")
class ClientController(clientServices: ClientServices) : BasicController<Client1, Int>(clientServices)
