package com.example.jeanjacquesbastien_countriesoftheworld.network

import com.example.jeanjacquesbastien_countriesoftheworld.utils.UIState
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getCountries(): Flow<UIState>
}