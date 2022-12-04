package com.example.demo

import com.example.demo.domain.Client1
import com.example.demo.domain.Product
import com.example.demo.domain.Provider1
import com.example.demo.service.ClientServices
import com.example.demo.service.ProductServices
import com.example.demo.service.ProviderServices
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class OnBoot(
    private val productServices: ProductServices,
    private val providerServices: ProviderServices,
    private val clientServices: ClientServices
) : ApplicationRunner {

//    private final val provider1 = providerServices.save(Provider1(1, "Luis Gomez", "Luis45@Gmail.com"))
//    private final val provider2 = providerServices.save(Provider1(2, "Pedro Flores", "PedroF123@Hotmail.com"))
//    private final val provider3 = providerServices.save(Provider1(3, "Lee John", "Lee21J@Yahoo.com"))

    private final val providers: MutableSet<Provider1> = mutableSetOf(
        Provider1(1, "Luis Gomez", "Luis45@Gmail.com"),
        Provider1(2, "Pedro Flores", "PedroF123@Hotmail.com"),
        Provider1(3, "Lee John", "Lee21J@Yahoo.com")
    )

    private final val products: MutableSet<Product> by lazy {
        mutableSetOf(
            Product(1, "Apple", 22.2, 14, providers.last()),
            Product(2, "Banana", 33.3, 5, providers.elementAt(1)),
            Product(3, "Orange", 15.7, 8, providers.elementAt(1)),
            Product(4, "Watermelon", 34.2, 12, providers.first()),
            Product(5, "Strawberry", 23.6, 1, providers.last()),
            Product(6, "Pineapple", 36.1, 9, providers.first())
        )
    }

    private final val clients: MutableSet<Client1> = mutableSetOf(
        Client1(1, "Fruit store one", "Carl Fuentes"),
        Client1(2, "Fruit store two", "John Max"),
        Client1(3, "Fruit store three", "Martha Minus"),
    )

    override fun run(args: ApplicationArguments?) {

        providers.forEach {
            if (!providerServices.providerDao.existsById(it.id)) {
                println("Saving -> ${it.id}")
                providerServices.save(it)
            }
        }

        products.forEach {
            if (!productServices.productDao.existsById(it.id)) {
                println("Saving -> ${it.id}")
                productServices.save(it)
            }
        }

        clients.forEach {
            if (!clientServices.clientDao.existsById(it.id)) {
                println("Saving -> ${it.id}")
                clientServices.save(it)
            }
        }

    }


}