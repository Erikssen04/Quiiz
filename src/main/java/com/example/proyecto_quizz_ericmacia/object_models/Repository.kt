package com.example.proyecto_quizz_ericmacia.object_models

import com.example.proyecto_quizz_ericmacia.models.Trivia

object Repository {
    val listTrivia: MutableList<Trivia> = mutableListOf(
        Trivia(
            id = 1,
            title = "Crash Bandicoot",
            description = "Juego de plataformas.",
            image = "im_crashbandicooot", // Nombre del recurso en drawable sin extensión
            creator = "Naughty Dog",
            questionCount = 10
        ),
        Trivia(
            id = 2,
            title = "Elden Ring",
            description = "Juego de rol en mundo abierto.",
            image = "im_eldenring",
            creator = "FromSoftware",
            questionCount = 15
        ),
        Trivia(
            id = 3,
            title = "GTA IV",
            description = "Acción en Liberty City.",
            image = "im_gtaiv",
            creator = "Rockstar Games",
            questionCount = 20
        ),
        Trivia(
            id = 4,
            title = "Half-Life",
            description = "Shooter en primera persona.",
            image = "im_halflife",
            creator = "Valve",
            questionCount = 12
        ),
        Trivia(
            id = 5,
            title = "The Legend of Zelda",
            description = "Aventura para salvar Hyrule.",
            image = "im_link",
            creator = "Nintendo",
            questionCount = 18
        ),
        Trivia(
            id = 6,
            title = "Super Mario",
            description = "Plataformas con Mario.",
            image = "im_mario",
            creator = "Nintendo",
            questionCount = 25
        ),
        Trivia(
            id = 7,
            title = "PlayStation 3",
            description = "Consola revolucionaria.",
            image = "im_ps3",
            creator = "Sony",
            questionCount = 8
        ),
        Trivia(
            id = 8,
            title = "Sonic the Hedgehog",
            description = "Velocidad con Sonic.",
            image = "im_sonic",
            creator = "Sega",
            questionCount = 14
        ),
        Trivia(
            id = 9,
            title = "Street Fighter",
            description = "Juego de lucha clásico.",
            image = "im_streetfighter",
            creator = "Capcom",
            questionCount = 10
        ),
        Trivia(
            id = 10,
            title = "Tekken",
            description = "Combate en 3D.",
            image = "im_tekken",
            creator = "Bandai Namco",
            questionCount = 13
        )
    )
}
