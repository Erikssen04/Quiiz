package com.example.proyecto_quizz_ericmacia.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.databinding.ActivityLoginBinding
import com.example.proyecto_quizz_ericmacia.databinding.FragmentMainMenuBinding
import com.example.proyecto_quizz_ericmacia.features.auth.LoginActivity

class MainMenuFragment : Fragment() {
    lateinit var bindingFragment: FragmentMainMenuBinding
    lateinit var loginBinding: ActivityLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = FragmentMainMenuBinding.inflate(inflater, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        // Establecer los listeners para los botones
    }

    private fun setupListeners() {
        bindingFragment.btnJugarTrivia.setOnClickListener {
            // Mensaje de que esta opción estará habilitada próximamente
            Toast.makeText(requireContext(), "Esta opción será habilitada proximamente",
                Toast.LENGTH_SHORT).show()
        }

        bindingFragment.btnBaulTrivias.setOnClickListener {
            // Navegar al fragmento del baúl de trivias
            findNavController().navigate(R.id.action_fragment_main_menu_to_fragment_baul_trivias)
        }

        bindingFragment.btnBaulJuegos.setOnClickListener {
            // Mensaje de que esta opción estará habilitada próximamente
            Toast.makeText(requireContext(), "Esta opción será habilitada proximamente",
                Toast.LENGTH_SHORT).show()
        }

        bindingFragment.btnSalir.setOnClickListener {
            borrar_sesion()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish() // Cierra la actividad
        }

        val correo = requireActivity().intent.getStringExtra("CORREO")
        // Mensaje de bienvenida al usuario
        bindingFragment.welcome.text = if (!correo.isNullOrEmpty()) {
            "¡Bienvenido a Quiiz: \n$correo!"
        } else {
            ""
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun borrar_sesion(){
        var borrar_sesion = requireContext().getSharedPreferences(LoginActivity.Global.preferencias_compartidas, Context.MODE_PRIVATE).edit()
        borrar_sesion.clear()
        borrar_sesion.apply()
        borrar_sesion.commit()

    }

}
