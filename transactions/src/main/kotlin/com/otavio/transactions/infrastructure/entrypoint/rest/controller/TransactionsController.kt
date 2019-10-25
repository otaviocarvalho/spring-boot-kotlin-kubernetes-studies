package com.otavio.transactions.infrastructure.entrypoint.rest.controller

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

@RestController
@Api("Transactions public API")
class TransactionsController {

    @GetMapping("/transactions")
    fun transactions(): List<Transaction> {
        return listOf(Transaction(id = UUID.randomUUID(), value = BigDecimal.TEN))
    }

    data class Transaction(val id: UUID, val value: BigDecimal)

}
