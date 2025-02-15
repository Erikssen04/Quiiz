package com.example.proyecto_quizz_ericmacia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_quizz_ericmacia.adapter.AdapterTrivia
import com.example.proyecto_quizz_ericmacia.controller.Controller
import com.example.proyecto_quizz_ericmacia.databinding.FragmentBaulTriviasBinding
import com.example.proyecto_quizz_ericmacia.dialogues.DialogNewTrivia

class BaulTriviasFragment : Fragment() {
    private lateinit var binding: FragmentBaulTriviasBinding
    private val Controller: Controller by viewModels()
    private lateinit var adapterTrivia: AdapterTrivia

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaulTriviasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterTrivia = AdapterTrivia(
            listTrivia = mutableListOf(),
            deleteOnClick = { trivia->
                Controller.eliminarTrivia(requireContext(), trivia)
            },
            updateOnClick = { trivia->
                Controller.actualizarTrivia(requireContext(), trivia)
            },
            controller = Controller,
            fragmentManager = childFragmentManager)

        // Configurar RecyclerView
        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.myRecyclerView.adapter = adapterTrivia

        // Observar datos de Firebase en tiempo real
        Controller.trivias.observe(viewLifecycleOwner) { lista ->
            adapterTrivia.actualizarLista(lista)
        }

        // Botón para agregar un nuevo elemento
        binding.btnAdd.setOnClickListener {
            DialogNewTrivia(
                onNewTriviaDialog = {trivia ->
                    Controller.agregarTrivia(requireContext(), trivia)
                }
            ).show(parentFragmentManager, "Nueva Trivia")
        }
    }
}
