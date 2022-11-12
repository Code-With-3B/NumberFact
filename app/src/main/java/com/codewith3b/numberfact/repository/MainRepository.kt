package com.codewith3b.numberfact.repository

import com.codewith3b.numberfact.api.request.NumbersAPIRequest

class MainRepository(private val apiReference:NumbersAPIRequest) {

    suspend fun getRandomFact():String = apiReference.getRandomFact()
    suspend fun getNumberFact(number:Long) = apiReference.getNumberFact(number)
}