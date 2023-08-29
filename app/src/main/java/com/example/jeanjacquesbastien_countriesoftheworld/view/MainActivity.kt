package com.example.jeanjacquesbastien_countriesoftheworld.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jeanjacquesbastien_countriesoftheworld.R
import com.example.jeanjacquesbastien_countriesoftheworld.databinding.ActivityMainBinding
import com.example.jeanjacquesbastien_countriesoftheworld.model.CountryResponseItem
import com.example.jeanjacquesbastien_countriesoftheworld.utils.CountryAdapter
import com.example.jeanjacquesbastien_countriesoftheworld.utils.UIState
import com.example.jeanjacquesbastien_countriesoftheworld.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val countryAdapter by lazy {
        CountryAdapter()
    }
    lateinit var countryViewModel: CountryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        configureObserver()
    }

    private fun configureObserver(){
        countryViewModel.countryLiveData.observe(this){state ->
            when(state){
                is UIState.Success<*> -> {
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(applicationContext)
                        countryAdapter.getNewData(state.response as List<CountryResponseItem>)
                        adapter = countryAdapter
                    }
                } else -> {}
            }

        }
    }
}