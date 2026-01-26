package com.example.rickmortyapp.presentation

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.rickmortyapp.databinding.ActivityDetailBinding
import com.example.rickmortyapp.presentation.viewmodel.DetailViewModel
import com.example.rickmortyapp.presentation.viewmodel.RickUiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.getIntExtra("ID", -1).takeIf { it != -1 }?.let { viewModel.load(it) }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressBar.isVisible = state is RickUiState.Loading
                    
                    (state as? RickUiState.Success)?.data?.firstOrNull()?.let { char ->
                        with(binding) {
                            ivCharacter.load(char.image)
                            tvName.text = char.name
                            tvStatusSpecies.text = "${char.status} - ${char.species}"
                            tvGender.text = char.gender
                            tvLocation.text = char.location
                            tvOrigin.text = char.origin
                            
                            val color = when (char.status.lowercase()) {
                                "alive" -> "#55CC44"
                                "dead" -> "#D63D2E"
                                else -> "#9E9E9E"
                            }
                            vStatusDot.background = GradientDrawable().apply {
                                shape = GradientDrawable.OVAL
                                setColor(Color.parseColor(color))
                            }
                        }
                    }
                    
                    (state as? RickUiState.Error)?.let {
                        Toast.makeText(this@DetailActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
