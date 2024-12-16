package com.example.proyecto_quizz_ericmacia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.models.Trivia

class AdapterTrivia(
    var listTrivia : MutableList<Trivia>,
    var deleteOnClick : (Int) -> Unit,
    var updateOnClick : (Int) -> Unit
) : RecyclerView.Adapter<ViewHolderTrivia>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTrivia {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemTrivia = R.layout.item_trivia
        return ViewHolderTrivia(
            layoutInflater.inflate(layoutItemTrivia, parent, false),
            deleteOnClick,
            updateOnClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolderTrivia, position: Int) {
        holder.renderize(listTrivia[position])
    }

    override fun getItemCount(): Int = listTrivia.size
}