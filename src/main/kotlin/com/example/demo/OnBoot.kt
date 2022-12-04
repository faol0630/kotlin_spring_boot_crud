package com.example.demo

import com.example.demo.domain.Product
import com.example.demo.domain.Provider1
import com.example.demo.service.ProductServices
import com.example.demo.service.ProviderServices
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class OnBoot(
    private val productServices: ProductServices,
    private val providerServices: ProviderServices
) : ApplicationRunner {

    private final val provider1 = providerServices.save(Provider1(1, "Luis Gomez", "Luis45@Gmail.com"))
    private final val provider2 = providerServices.save(Provider1(2, "Pedro Flores", "PedroF123@Hotmail.com"))
    private final val provider3 = providerServices.save(Provider1(3, "Lee John", "Lee21J@Yahoo.com"))

    val products: MutableSet<Product> = mutableSetOf(
        Product(1, "Apple", 22.2, 14, provider3),
        Product(2, "Banana", 33.3, 5, provider2),
        Product(3, "Orange", 15.7, 8, provider2),
        Product(4, "Watermelon", 34.2, 12, provider1),
        Product(5, "Strawberry", 23.6, 1, provider3),
        Product(6, "Pineapple", 36.1, 9, provider1)
    )

    override fun run(args: ApplicationArguments?) {

        products.forEach {
            if (!productServices.productDao.existsById(it.id)) {
                println("Saving -> ${it.id}")
                productServices.save(it)
            }
        }

    }
}