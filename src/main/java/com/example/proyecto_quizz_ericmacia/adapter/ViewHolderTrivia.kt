package com.example.proyecto_quizz_ericmacia.adapter

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.controller.Controller
import com.example.proyecto_quizz_ericmacia.databinding.ItemTriviaBinding
import com.example.proyecto_quizz_ericmacia.dialogues.DialogEditTrivia
import com.example.proyecto_quizz_ericmacia.models.Trivia

class ViewHolderTrivia (
    view: View,
    var deleteOnClick: (Trivia) -> Unit,
    var updateOnClick: (Trivia) -> Unit,
    var controller: Controller,
    var fragmentManager: FragmentManager
) : RecyclerView.ViewHolder (view) {

    var binding: ItemTriviaBinding = ItemTriviaBinding.bind(view)

    fun renderize(trivia: Trivia) {
        val isDrawableResource = !trivia.image.startsWith("http")

        if (isDrawableResource) {
            // Si la imagen es un recurso drawable, obtener su ID
            val imageId = itemView.context.resources.getIdentifier(
                trivia.image, "drawable", itemView.context.packageName
            )

            if (imageId != 0) {
                Glide
                    .with(itemView.context)
                    .load(imageId)  // Cargar recurso drawable
                    .centerCrop()    // Aplicar centerCrop para ajustar la imagen
                    .placeholder(R.drawable.placeholder_image)  // Placeholder mientras carga
                    .error(R.drawable.error_image)  // Imagen de error en caso de fallo
                    .into(binding.ivTrivia)
            } else {
                // Si el recurso no se encuentra, se carga una imagen de error
                Glide
                    .with(itemView.context)
                    .load(R.drawable.error_image)
                    .into(binding.ivTrivia)
            }
        } else {
            // Si la imagen es una URL, usarla directamente
            Glide
                .with(itemView.context)
                .load(trivia.image)
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(binding.ivTrivia)
        }

        binding.txtviewTitle.setText(trivia.title)
        binding.txtviewDesc.setText(trivia.description)
        binding.txtviewCreator.setText(trivia.creator)
        binding.txtviewNumPreguntas.text = "${trivia.questionCount} preguntas"

        setOnClickListener(trivia)
    }

    private fun setOnClickListener(trivia : Trivia){
        binding.btnEdit.setOnClickListener {
            DialogEditTrivia(
                triviaToUpdate = trivia,
                nUpdateTriviaDialog = {trivia ->
                    controller.actualizarTrivia(itemView.context, trivia)
                }
            ).show(fragmentManager, "Nueva Trivia")
            updateOnClick(trivia)
        }
        binding.btnDelete.setOnClickListener {
            deleteOnClick(trivia)
        }
    }
}
