package com.example.proyecto_quizz_ericmacia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_quizz_ericmacia.features.auth.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // ---------- VARIABLES ------------

        val btnTriviaPred = findViewById<AppCompatButton>(R.id.btn_jugarTriviaPred)
        val btnLogin = findViewById<AppCompatButton>(R.id.btn_login)

        // ---------- LISTENERS -----------

        btnTriviaPred.setOnClickListener {
            Toast.makeText(this, "Esta opción será habilitada proximamente",
                Toast.LENGTH_SHORT).show()
        }

        btnLogin.setOnClickListener {
            println("Clic en el botón, abriendo LoginActivity")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}