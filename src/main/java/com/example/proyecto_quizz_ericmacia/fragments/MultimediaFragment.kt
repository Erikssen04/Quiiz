package com.example.proyecto_quizz_ericmacia.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import com.bumptech.glide.Glide
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.databinding.FragmentMultimediaBinding

class MultimediaFragment : Fragment() {
    lateinit var bindingFragment: FragmentMultimediaBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = FragmentMultimediaBinding.inflate(inflater, container, false)

        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoView = view.findViewById<VideoView>(R.id.multimediaVideo)
        videoView?.let {
            val videoPath = "android.resource://" + requireContext().packageName + "/" + R.raw.video
            it.setVideoURI(Uri.parse(videoPath))

            it.setOnPreparedListener { mediaPlayer ->

                //Silenciar el volumen del vídeo para que solo se escuche el de sonido.mp3
                mediaPlayer.setVolume(0f, 0f)
            }

            it.start()
        } ?: run {
            Log.e("MultimediaFragment", "Error: videoView es null")
        }

        val imageView = bindingFragment.multimediaImage
        val imageUrl = R.drawable.quiiz_logo_v1
        Glide.with(this).load(imageUrl).into(imageView)

    }

}