package com.example.proyecto_quizz_ericmacia.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto_quizz_ericmacia.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var bindingFragment: FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = FragmentProfileBinding.inflate(inflater, container, false)

        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("sharedpreferences", Context.MODE_PRIVATE)
        val correo = sharedPreferences.getString("CORREO", "Correo no disponible")

        bindingFragment.profileEmail.text = "Correo electrónico: \n$correo"

    }

}