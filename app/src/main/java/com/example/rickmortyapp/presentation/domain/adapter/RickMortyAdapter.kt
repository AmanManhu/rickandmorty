package com.example.rickmortyapp.presentation.domain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickmortyapp.domain.model.Character
import com.example.rickmortyapp.databinding.ItemRickListBinding

class RickMortyAdapter(private val onItemClick: (Int) -> Unit) : ListAdapter<Character, RickMortyAdapter.ViewHolder>(RickDiffCallback()) {

    class ViewHolder(private val binding: ItemRickListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character, onItemClick: (Int) -> Unit) {
            binding.tvName.text = item.name
            binding.tvStatus.text = item.status
            binding.ivCharacter.load(item.image)
            binding.root.setOnClickListener { onItemClick(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRickListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClick)
    }

    class RickDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}
