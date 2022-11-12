package com.codewith3b.numberfact.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewith3b.numberfact.api.request.NumbersAPIRequest
import com.codewith3b.numberfact.repository.MainRepository
import kotlinx.coroutines.launch

class NumberViewModel : ViewModel() {
    var fact: String by mutableStateOf("Search Random Fact")

    private val repository: MainRepository = MainRepository(NumbersAPIRequest.getInstance())

    fun getRandomFact() {
        viewModelScope.launch {
            fact = repository.getRandomFact()
            Log.e("Random", fact)
        }
    }

    fun getNumberFact(number: Long) {
        viewModelScope.launch {
            fact = repository.getNumberFact(number)
            Log.e("FACT", fact)
        }
    }
}