package com.example.proyecto_quizz_ericmacia.controller

import android.content.Context
import com.example.proyecto_quizz_ericmacia.models.Trivia
import com.example.proyecto_quizz_ericmacia.object_models.Repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class Controller : ViewModel() {
    private val _trivias = MutableLiveData<List<Trivia>>()
    val trivias: LiveData<List<Trivia>> get() = _trivias

    init {
        obtenerTrivias()

    }
    // Obtener datos desde Firebase en tiempo real
    private fun obtenerTrivias() {
        Repository.getTrivias { lista ->
            _trivias.postValue(lista)
        }
    }

    fun agregarTrivia(context: Context, trivia: Trivia) {
        viewModelScope.launch {
            Repository.addTrivia(context, trivia)
            obtenerTrivias()
        }
    }

    fun actualizarTrivia(context: Context, trivia: Trivia) {
        viewModelScope.launch {
            Repository.updateTrivia(context, trivia)
            obtenerTrivias()
        }
    }

    fun eliminarTrivia(context: Context,trivia: Trivia) {
        viewModelScope.launch {
            Repository.deleteTrivia(context, trivia.id)
            obtenerTrivias()
        }
    }
}
