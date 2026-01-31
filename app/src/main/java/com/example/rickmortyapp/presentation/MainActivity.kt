package com.example.rickmortyapp.presentation

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.example.rickmortyapp.databinding.ActivityMainBinding
import com.example.rickmortyapp.presentation.domain.adapter.PagingRickMortyAdapter
import com.example.rickmortyapp.presentation.viewmodel.RickViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    private val viewModel: RickViewModel by viewModel()
    
    private val adapter = PagingRickMortyAdapter { id ->
        startActivity(Intent(this, DetailActivity::class.java).apply { putExtra("ID", id) })
    }

    override fun initialize() {
        binding.rvCharacters.adapter = adapter
        setupObserve()
    }

    private fun setupObserve() {
        lifecycleScope.launch {
            viewModel.characters.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}
