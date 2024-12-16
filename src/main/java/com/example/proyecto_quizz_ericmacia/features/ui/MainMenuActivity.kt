package com.example.proyecto_quizz_ericmacia.features.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_quizz_ericmacia.R

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)

        // obtiene el usuario del Intent declarado en el LoginActivity
        val usuario = intent.getStringExtra("USUARIO")

        // Muestra mensaje de bienvenida al usuario
        val welcome = findViewById<TextView>(R.id.welcome)

        if (usuario != null && usuario.isNotEmpty()){
            welcome.text = "¡Bienvenido a Quiiz, $usuario!"
        } else {
            welcome.text = ""
        }

        val btnTrivia = findViewById<AppCompatButton>(R.id.btn_jugarTrivia)
        val btnBaulTrivias = findViewById<AppCompatButton>(R.id.btn_baulTrivias)
        val btnBaulJuegos = findViewById<AppCompatButton>(R.id.btn_baulJuegos)

        // ---------- LISTENERS -----------

        btnTrivia.setOnClickListener {
            Toast.makeText(this, "Esta opción será habilitada proximamente",
                Toast.LENGTH_SHORT).show()
        }

        btnBaulTrivias.setOnClickListener {
            println("Clic en el botón, abriendo BaulTriviasActivity")
            val intent = Intent(this, BaulTriviasActivity::class.java)
            startActivity(intent)
        }

        btnBaulJuegos.setOnClickListener {
            Toast.makeText(this, "Esta opción será habilitada proximamente",
                Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}