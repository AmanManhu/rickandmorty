package com.example.rickmortyapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity <VB : ViewBinding>(private val inflate:
        (LayoutInflater) -> VB) : AppCompatActivity() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }
    abstract fun init()
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}