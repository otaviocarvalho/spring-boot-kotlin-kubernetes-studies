package com.otavio.transactionsreader.infrastructure.entrypoint.rest.controller

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import java.io.Serializable
import java.math.BigDecimal
import java.util.UUID

@RestController
@Api("Transactions reader public API")
class TransactionsReaderController {

    val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val client : OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl("http://transactions:8080")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build()

    @GetMapping("/transations-feed")
    fun transactionsFeed(): List<Response> {
        val transactionsApi = retrofit.create(TransactionsApi::class.java)

        return transactionsApi.getTransactions().execute().body()!!
    }

}

interface TransactionsApi {
    @GET("/transactions")
    fun getTransactions(): Call<List<Response>>
}

data class Response(
        @JsonProperty("id") val id : UUID,
        @JsonProperty("value") val value : BigDecimal) : Serializable

