package com.example.proyecto_quizz_ericmacia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.controller.Controller
import com.example.proyecto_quizz_ericmacia.models.Trivia

class AdapterTrivia(
    var listTrivia : MutableList<Trivia>,
    var deleteOnClick : (Trivia) -> Unit,
    var updateOnClick : (Trivia) -> Unit,
    var controller: Controller,
    var fragmentManager: FragmentManager
) : RecyclerView.Adapter<ViewHolderTrivia>(){

    // Inflamos la vista de cada item y la pasamos al ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTrivia {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderTrivia(
            layoutInflater.inflate(R.layout.item_trivia, parent, false),
            deleteOnClick,
            updateOnClick,
            controller,
            fragmentManager = fragmentManager
        )
    }

    // Vinculamos los datos de la trivia con el ViewHolder
    override fun onBindViewHolder(holder: ViewHolderTrivia, position: Int) {
        holder.renderize(listTrivia[position])
    }

    // Devuelve la cantidad de elementos en la lista
    override fun getItemCount(): Int = listTrivia.size

    fun actualizarLista(nuevaLista: List<Trivia>) {
        listTrivia.clear()
        listTrivia.addAll(nuevaLista)
        notifyDataSetChanged() // Notificar que los datos han cambiado
    }
}
