package com.example.proyecto_quizz_ericmacia.dao

import com.example.proyecto_quizz_ericmacia.interfaces.TriviaDAO
import com.example.proyecto_quizz_ericmacia.models.Trivia
import com.example.proyecto_quizz_ericmacia.object_models.Repository

class TriviaDAOimp private constructor(): TriviaDAO{
    companion object {
        val myDao: TriviaDAOimp by lazy{
            TriviaDAOimp()
        }
    }

    override fun getDataTrivias(): List<Trivia> = Repository.listTrivia

}