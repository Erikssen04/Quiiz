package com.example.proyecto_quizz_ericmacia.features.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_quizz_ericmacia.adapter.AdapterTrivia
import com.example.proyecto_quizz_ericmacia.controller.Controller
import com.example.proyecto_quizz_ericmacia.databinding.ActivityBaulTriviasBinding
import com.example.proyecto_quizz_ericmacia.object_models.Repository

class BaulTriviasActivity : AppCompatActivity() {
    lateinit var binding: ActivityBaulTriviasBinding // Usa el binding de esta actividad
    lateinit var controller: Controller // El controlador para manejar los datos
    private lateinit var adapter: AdapterTrivia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el binding
        binding = ActivityBaulTriviasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el RecyclerView y el Controller
        initRecyclerView()
        controller = Controller(this, binding) // Pasamos el binding al controller
        controller.setAdapter()

    }

    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this) // Establece el layout manager

    }
}