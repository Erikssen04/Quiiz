package com.example.proyecto_quizz_ericmacia.features.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.dialogues.DialogCreateAccount
import com.example.proyecto_quizz_ericmacia.features.ui.MainMenuActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    object Global{
        var preferencias_compartidas="sharedpreferences"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        verificar_sesion_abierta()

        // --------- VARIABLES -----------

        val inputLayoutCorreo = findViewById<TextInputLayout>(R.id.nombreCorreo)
        val inputLayoutContrasena = findViewById<TextInputLayout>(R.id.contrasena)
        val btnLogin = findViewById<Button>(R.id.iniciarSesion)
        val btnSignUp = findViewById<Button>(R.id.registrarse)
        val resetContrasena = findViewById<TextView>(R.id.resetContrasena)

        val editCorreo = inputLayoutCorreo.editText
        val editContrasena = inputLayoutContrasena.editText

        // ---------- LISTENERS ----------

        btnLogin.setOnClickListener {
            if (editContrasena != null) {
                if(editCorreo != null && Patterns.EMAIL_ADDRESS.matcher(editCorreo.text.toString()).matches()){
                    login_firebase(editCorreo.text.toString(), editContrasena.text.toString())
                } else {
                    Toast.makeText(applicationContext, "Formato de correo inválido", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "Por favor, escriba la contraseña", Toast.LENGTH_LONG).show()
            }
        }

        btnSignUp.setOnClickListener {
            DialogCreateAccount().show(supportFragmentManager,null)
        }

        resetContrasena.setOnClickListener {
            if(editCorreo != null){
                if(Patterns.EMAIL_ADDRESS.matcher(editCorreo.text.toString()).matches()){
                    reset_contrasena(editCorreo.text.toString())
                }else{
                    Toast.makeText(this, "Formato de correo incorrecto", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Introduce un correo", Toast.LENGTH_LONG).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun login_firebase(correo: String, contrasena: String) {
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if(user != null && user.isEmailVerified) {
                        val intent = Intent(this, MainMenuActivity::class.java)
                        intent.putExtra("CORREO", correo)
                        startActivity(intent);

                        guardar_sesion(correo)
                    }else{
                        Toast.makeText(this, "Verifica tu correo antes de iniciar sesión.", Toast.LENGTH_LONG).show()
                        auth.signOut()
                    }
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
            }

    }

    fun reset_contrasena(email: String) {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Se ha enviado el correo de recuperación de contraseña", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "El correo ingresado no existe", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun verificar_sesion_abierta(){
        var sesion_abierta: SharedPreferences=this.getSharedPreferences(Global.preferencias_compartidas, Context.MODE_PRIVATE)

        var correo = sesion_abierta.getString("CORREO", null)

        if (correo != null) {
            var intent = Intent(applicationContext, MainMenuActivity::class.java)
            intent.putExtra("CORREO", correo)
            startActivity(intent)
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun guardar_sesion(correo: String){
        var guardar_sesion: SharedPreferences.Editor=this.getSharedPreferences(Global.preferencias_compartidas, Context.MODE_PRIVATE).edit()
        guardar_sesion.putString("CORREO", correo)
        guardar_sesion.apply()
        guardar_sesion.commit()

    }


}
