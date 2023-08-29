package com.example.jeanjacquesbastien_countriesoftheworld.utils

sealed class UIState {
    object Loading: UIState()
    class Success <T> (val response : T) :UIState()
    class Error (val exception: Exception): UIState()
}
