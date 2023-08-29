package com.example.jeanjacquesbastien_countriesoftheworld.network

import com.example.jeanjacquesbastien_countriesoftheworld.model.CountryResponse
import com.example.jeanjacquesbastien_countriesoftheworld.utils.COUNTRY_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {
    @GET(COUNTRY_ENDPOINT)
    suspend fun fetchCountries(): Response<CountryResponse>
}