package com.example.jeanjacquesbastien_countriesoftheworld.network

import com.example.jeanjacquesbastien_countriesoftheworld.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepositoryImplementation @Inject constructor(private val service: CountryApiService) : CountryRepository {
    override suspend fun getCountries(): Flow<UIState> {
        return flow {
            emit(UIState.Loading)
            try{
                val response = service.fetchCountries()
                if(response.isSuccessful){
                    response.body()?.let{
                        emit(UIState.Success(it))
                    }?: throw Exception("No values available")
                } else throw  Exception("Api called failed")

            }
            catch (e:Exception){
                emit(UIState.Error(e))
            }
        }
    }
}