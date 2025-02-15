package com.example.proyecto_quizz_ericmacia.dialogues

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.features.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class DialogCreateAccount : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.fragment_dialog_create_account, container, false)

        // --------- VARIABLES -----------

        val correo = view.findViewById<EditText>(R.id.emailInput)
        val contrasena = view.findViewById<EditText>(R.id.passwordInput)
        val btnSignUp = view.findViewById<Button>(R.id.btn_crear_cuenta)

        // ---------- LISTENERS ----------

        btnSignUp.setOnClickListener {
            if (contrasena != null) {
                if(correo != null && Patterns.EMAIL_ADDRESS.matcher(correo.text.toString()).matches()){
                    create_account_firebase(correo.text.toString(), contrasena.text.toString())
                } else {
                    Toast.makeText(requireContext(), "Formato de correo inválido", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Por favor, escriba la contraseña", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun create_account_firebase(correo: String, contrasena: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {

                    val user = task.result.user
                    user?.sendEmailVerification()?.addOnCompleteListener { emailTask ->
                        if (emailTask.isSuccessful) {

                            var guardar_sesion: LoginActivity = activity as LoginActivity
                            guardar_sesion.guardar_sesion(user.isEmailVerified.toString())

                            Toast.makeText(requireContext(), "Cuenta creada exitosamente. Verifica tu correo", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(requireContext(), "Error: Verificación no enviada", Toast.LENGTH_LONG).show()
                        }
                    }

                } else {
                    Toast.makeText(requireContext(), "Datos ingresados no válidos", Toast.LENGTH_LONG).show()
                }
            }
    }
}