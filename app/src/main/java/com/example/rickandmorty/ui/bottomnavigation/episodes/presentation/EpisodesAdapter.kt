package com.example.rickandmorty.ui.bottomnavigation.episodes.presentation

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemEpisodesBinding
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersClickListener
import com.example.rickandmorty.ui.bottomnavigation.episodes.data.response.EpisodeResult

class EpisodesAdapter(
    val context: Context,
) : ListAdapter<EpisodeResult, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<EpisodeResult>() {
        override fun areItemsTheSame(
            oldItem: EpisodeResult,
            newItem: EpisodeResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: EpisodeResult,
            newItem: EpisodeResult
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            ItemEpisodesBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentCharacter = getItem(position)
        when (holder) {
            is ImageViewHolder -> holder.bindImage(currentCharacter!!)
        }
    }

    inner class ImageViewHolder(val itemBinding: ItemEpisodesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindImage(model: EpisodeResult) {
            itemBinding.run {
             episode.text=model.episode
                pilot.text=model.name
                releaseDate.text=model.air_date

                }
            }
        }
    }

