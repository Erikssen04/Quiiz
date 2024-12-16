package com.example.proyecto_quizz_ericmacia.interfaces

import com.example.proyecto_quizz_ericmacia.models.Trivia

interface TriviaDAO {
    fun getDataTrivias(): List<Trivia>
    //fun deleteTrivia(trivia : Trivia)
}