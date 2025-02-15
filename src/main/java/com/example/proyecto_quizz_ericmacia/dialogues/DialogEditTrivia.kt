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
import com.example.proyecto_quizz_ericmacia.models.ArgumentsTrivia
import com.example.proyecto_quizz_ericmacia.models.Trivia

class DialogEditTrivia(
    val triviaToUpdate: Trivia,
    val nUpdateTriviaDialog: (Trivia) -> Unit
) : DialogFragment() {

    val ARGUMENT_ID: String = ArgumentsTrivia.ARGUMENT_ID
    val ARGUMENT_TITLE: String = ArgumentsTrivia.ARGUMENT_TITLE
    val ARGUMENT_DESC: String = ArgumentsTrivia.ARGUMENT_DESC
    val ARGUMENT_IMAGE: String = ArgumentsTrivia.ARGUMENT_IMAGE
    val ARGUMENT_CREATOR: String = ArgumentsTrivia.ARGUMENT_CREATOR
    val ARGUMENT_QCOUNT: String = ArgumentsTrivia.ARGUMENT_QCOUNT

    // Inicializa los argumentos para pasar a la vista del diálogo
    init {
        val args = Bundle().apply {
            putString(ARGUMENT_ID, triviaToUpdate.id)
            putString(ARGUMENT_TITLE, triviaToUpdate.title)
            putString(ARGUMENT_DESC, triviaToUpdate.description)
            putString(ARGUMENT_IMAGE, triviaToUpdate.image)
            putString(ARGUMENT_CREATOR, triviaToUpdate.creator)
            putInt(ARGUMENT_QCOUNT, triviaToUpdate.questionCount)
        }
        this.arguments = args
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // Crea el diálogo de edición de trivia
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext()) // Usamos el contexto del fragmento
        val inflater = requireActivity().layoutInflater
        val viewDialogEditTrivia = inflater.inflate(R.layout.dialog_new_trivia, null)
        setValuesIntoDialog(viewDialogEditTrivia, this.arguments)

        builder.setView(viewDialogEditTrivia)
            .setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, id ->
                    val updateTrivia = recoverDataLayout(viewDialogEditTrivia) as Trivia
                    if (updateTrivia.title.isNullOrEmpty() ||
                        updateTrivia.description.isNullOrEmpty() ||
                        updateTrivia.creator.isNullOrEmpty()
                    ) {
                        Toast.makeText(context, "Campos vacíos detectados", Toast.LENGTH_LONG).show()
                        getDialog()?.cancel()
                    } else {
                        nUpdateTriviaDialog(updateTrivia)
                    }
                })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id -> getDialog()?.cancel() })
        return builder.create()
    }

    private fun setValuesIntoDialog(viewDialogEditTrivia: View, arguments: Bundle?) {
        val binding = DialogNewTriviaBinding.bind(viewDialogEditTrivia)
        if (arguments != null) {
            //binding.txtViewId.setText(arguments.getString(ARGUMENT_ID))
            binding.txtViewTitle.setText(arguments.getString(ARGUMENT_TITLE))
            binding.txtViewDesc.setText(arguments.getString(ARGUMENT_DESC))
            binding.txtViewImage.setText(arguments.getString(ARGUMENT_IMAGE))
            binding.txtViewCreator.setText(arguments.getString(ARGUMENT_CREATOR))
            binding.txtViewNumPreguntas.setText(arguments.getInt(ARGUMENT_QCOUNT).toString())

        }
    }

    private fun recoverDataLayout(view: View): Any {
        val binding = DialogNewTriviaBinding.bind(view)
        return Trivia(
            id = triviaToUpdate.id,
            title = binding.txtViewTitle.text.toString(),
            description = binding.txtViewDesc.text.toString(),
            image = binding.txtViewImage.text.toString(),
            creator = binding.txtViewCreator.text.toString(),
            questionCount = binding.txtViewNumPreguntas.text.toString().toIntOrNull() ?: 0
        )
    }
}
