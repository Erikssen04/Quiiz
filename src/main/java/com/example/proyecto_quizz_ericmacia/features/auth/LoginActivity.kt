package com.example.proyecto_quizz_ericmacia.features.auth

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.features.ui.MainMenuActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val usuariosRegistrados = mutableMapOf<String, String>(
        "Erikssen" to "0000"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // --------- VARIABLES -----------

        val inputLayoutUsuario = findViewById<TextInputLayout>(R.id.nombreUsuario)
        val inputLayoutContrasena = findViewById<TextInputLayout>(R.id.contrasena)
        val btnLogin = findViewById<Button>(R.id.iniciarSesion)
        val btnSignUp = findViewById<Button>(R.id.registrarse)

        val editUsuario = inputLayoutUsuario.editText
        val editContrasena = inputLayoutContrasena.editText

        // ---------- LISTENERS ----------

        btnLogin.setOnClickListener {
            val usuario = editUsuario?.text.toString()
            val contrasena = editContrasena?.text.toString()
            startLogin(usuario, contrasena)
        }

        btnSignUp.setOnClickListener {
            showSignUpDialog()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun isLogeado(usuario: String, contrasena: String): Boolean {
        return usuariosRegistrados[usuario] == contrasena
    }

    private fun startLogin(usuario: String, contrasena: String) {
        if (isLogeado(usuario, contrasena)) {
            val intent = Intent(this, MainMenuActivity::class.java)
            intent.putExtra("USUARIO", usuario)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(
                this,
                "Error: Usuario o contraseña incorrectos / Usuario no existe",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showSignUpDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registro de Usuario")

        val dialogLayout = layoutInflater.inflate(R.layout.dialog_sign_up, null)
        val usernameInput = dialogLayout.findViewById<EditText>(R.id.usernameInput)
        val passwordInput = dialogLayout.findViewById<EditText>(R.id.passwordInput)

        builder.setView(dialogLayout)
        builder.setPositiveButton("Registrar") { _, _ ->
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
            } else if (usuariosRegistrados.containsKey(username)) {
                Toast.makeText(this, "El usuario $username ya existe.", Toast.LENGTH_SHORT).show()
            } else {
                usuariosRegistrados[username] = password
                Toast.makeText(this, "Usuario $username registrado exitosamente.", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}
