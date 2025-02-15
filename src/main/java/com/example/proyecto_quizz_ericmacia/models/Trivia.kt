package com.example.proyecto_quizz_ericmacia.models

class Trivia(
    var id: String,
    var title: String,
    var description: String,
    var image: String,
    var creator: String,
    var questionCount: Int
) {
    constructor() : this("", "", "", "", "", 0)
    override fun toString(): String {
        return "Trivia(id='$id', title='$title', description='$description', image='$image', creator=$creator, questionCount=$questionCount)"
    }
}
