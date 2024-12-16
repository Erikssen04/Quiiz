package com.example.proyecto_quizz_ericmacia.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.databinding.ItemTriviaBinding
import com.example.proyecto_quizz_ericmacia.features.ui.BaulTriviasActivity
import com.example.proyecto_quizz_ericmacia.models.Trivia

class ViewHolderTrivia (
    view: View,
    var deleteOnClick: (Int) -> Unit,
    var updateOnClick: (Int) -> Unit
) : RecyclerView.ViewHolder (view) {

    var binding: ItemTriviaBinding = ItemTriviaBinding.bind(view)

    fun renderize(trivia: Trivia) {
        val isDrawableResource = !trivia.image.startsWith("http")

        if (isDrawableResource) {
            val imageId = itemView.context.resources.getIdentifier(
                trivia.image, "drawable", itemView.context.packageName
            )

            if (imageId != 0) {
                Glide
                    .with(itemView.context)
                    .load(imageId)
                    .centerCrop()
                    .into(binding.ivTrivia)
            } else {
                Glide
                    .with(itemView.context)
                    .load(R.drawable.error_image)
                    .into(binding.ivTrivia)
            }
        } else {
            Glide
                .with(itemView.context)
                .load(trivia.image)
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .into(binding.ivTrivia)
        }

        binding.txtviewTitle.setText(trivia.title)
        binding.txtviewDesc.setText(trivia.description)
        binding.txtviewCreator.setText(trivia.creator)
        binding.txtviewNumPreguntas.text = "${trivia.questionCount} preguntas"

        setOnClickListener(adapterPosition)
    }

    private fun setOnClickListener(position : Int){
        binding.btnEdit.setOnClickListener {
            updateOnClick(position)
        }
        binding.btnDelete.setOnClickListener {
            deleteOnClick(position)
        }
    }


}