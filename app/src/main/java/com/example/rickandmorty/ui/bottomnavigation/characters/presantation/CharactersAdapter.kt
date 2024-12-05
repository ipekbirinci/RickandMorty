package com.example.rickandmorty.ui.bottomnavigation.characters.presantation

import android.content.Context
import android.graphics.drawable.ColorDrawable
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
import com.example.rickandmorty.databinding.ItemCharactersBinding
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersClickListener

class CharactersAdapter(
    val context: Context,
    val academyPlannedClickListener: CharactersClickListener
) : ListAdapter<Characters, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<Characters>() {
        override fun areItemsTheSame(
            oldItem: Characters,
            newItem: Characters
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Characters,
            newItem: Characters
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            ItemCharactersBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentCharacter = getItem(position)  // Artık Characters nesnesi dönecek
        when (holder) {
            is ImageViewHolder -> holder.bindImage(currentCharacter!!)
        }
    }

    inner class ImageViewHolder(val itemBinding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindImage(model: Characters) {
            itemBinding.run {
                itemName.text = model.name
                itemDescription.text = model.species
                isAlive.text = model.status
                val placeholderColor =
                    ColorDrawable(ContextCompat.getColor(itemImage.context, R.color.lines))
                Glide.with(itemName).load(model.url)
                    .placeholder(placeholderColor)
                    .transform(CenterCrop(), RoundedCorners(50)).into(itemImage)
                this.root.setOnClickListener {
                    academyPlannedClickListener.characterItemClicked(model)
                }
            }
        }
    }
}
