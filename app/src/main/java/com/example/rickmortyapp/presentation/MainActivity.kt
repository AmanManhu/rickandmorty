package com.example.rickmortyapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickmortyapp.databinding.ActivityMainBinding
import com.example.rickmortyapp.presentation.domain.adapter.RickMortyAdapter
import com.example.rickmortyapp.presentation.viewmodel.RickUiState
import com.example.rickmortyapp.presentation.viewmodel.RickViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: RickViewModel by viewModel()
    private val adapter = RickMortyAdapter { id ->
        startActivity(Intent(this, DetailActivity::class.java).apply { putExtra("ID", id) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvCharacters.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressBar.isVisible = state is RickUiState.Loading
                    
                    (state as? RickUiState.Success)?.let { adapter.submitList(it.data) }
                    (state as? RickUiState.Error)?.let { 
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show() 
                    }
                }
            }
        }
    }
}
