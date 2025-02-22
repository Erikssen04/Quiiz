package com.example.proyecto_quizz_ericmacia.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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

        val videoView = view.findViewById<VideoView>(R.id.videoView)
        videoView?.let {
            val videoPath = "android.resource://" + requireContext().packageName + "/" + R.raw.video
            it.setVideoURI(Uri.parse(videoPath))

            it.setOnPreparedListener { mediaPlayer ->

                //Silenciar el volumen del vídeo para que solo se escuche el de sonido.mp3
                mediaPlayer.setVolume(0f, 0f)
            }

            it.start()
        } ?: run {
            Log.e("MainMenuFragment", "Error: videoView es null")
        }

        val imageView = bindingFragment.quiizMainActLogo

        // He optado por el mismo logo del drawable en lugar de una URL
        val imageUrl = R.drawable.quiiz_logo_v1

        Glide.with(this).load(imageUrl).into(imageView)

        setupListeners()
    }

    private fun setupListeners() {
        bindingFragment.btnJugarTrivia.setOnClickListener {
            // Mensaje de que esta opción estará habilitada próximamente
            Toast.makeText(requireContext(), "Esta opción será habilitada proximamente",
                Toast.LENGTH_SHORT).show()
        }

        bindingFragment.btnBaulTrivias.setOnClickListener {
            // Navegar al fragmento del baúl de trivias
            findNavController().navigate(R.id.action_mainMenu_to_baulTrivias)
        }

        bindingFragment.btnBaulJuegos.setOnClickListener {
            // Mensaje de que esta opción estará habilitada próximamente
            Toast.makeText(requireContext(), "Esta opción será habilitada proximamente",
                Toast.LENGTH_SHORT).show()
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
