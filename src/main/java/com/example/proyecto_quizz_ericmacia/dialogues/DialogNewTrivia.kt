package com.example.proyecto_quizz_ericmacia.dialogues

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.databinding.DialogNewTriviaBinding
import com.example.proyecto_quizz_ericmacia.models.Trivia

class DialogNewTrivia(

    val onNewTriviaDialog: (Trivia) -> Unit

) : DialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext()) // Usamos el contexto del fragmento
        val inflater = requireActivity().layoutInflater
        val viewDialogNewTrivia = inflater.inflate(R.layout.dialog_new_trivia, null)

        builder.setView(viewDialogNewTrivia)
            .setPositiveButton("Añadir",
                DialogInterface.OnClickListener { dialog, id ->
                    val newTrivia = recoverDataLayout(viewDialogNewTrivia) as Trivia
                    if (newTrivia.title.isNullOrEmpty() ||
                        newTrivia.description.isNullOrEmpty() ||
                        newTrivia.image.isNullOrEmpty() ||
                        newTrivia.creator.isNullOrEmpty()
                    ) {
                        Toast.makeText(context, "Campos vacíos detectados", Toast.LENGTH_LONG).show()
                        getDialog()?.cancel()
                    } else {
                        onNewTriviaDialog(newTrivia)
                    }
                })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id -> getDialog()?.cancel() })
        return builder.create()
    }

    private fun recoverDataLayout(view: View): Any {
        val binding = DialogNewTriviaBinding.bind(view)
        return Trivia(
            id = "",
            title = binding.txtViewTitle.text.toString(),
            description = binding.txtViewDesc.text.toString(),
            image = binding.txtViewImage.text.toString(),
            creator = binding.txtViewCreator.text.toString(),
            questionCount = binding.txtViewNumPreguntas.text.toString().toIntOrNull() ?: 0
        )
    }
}