package com.example.proyecto_quizz_ericmacia.interfaces

import com.example.proyecto_quizz_ericmacia.models.Trivia

interface TriviaOnClickDialogDAO {
    fun onEditTriviaDialog(trivia: Trivia, pos: Int)
    fun onAddTriviaDialog(trivia: Trivia)
    fun onDeleteTriviaDialog(pos: Int)
}