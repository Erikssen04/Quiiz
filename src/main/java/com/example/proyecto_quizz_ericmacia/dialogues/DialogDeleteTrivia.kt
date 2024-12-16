package com.example.proyecto_quizz_ericmacia.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogDeleteTrivia(
    val pos : Int,
    val name : String,
    val onDeleteHotelDialog: (Int) -> Unit
) : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setMessage("¿Deseas borrar la trivia $name?")
                .setPositiveButton("Si",
                    DialogInterface.OnClickListener {
                            dialog, id ->
                        onDeleteHotelDialog(pos)
                    })
                .setNegativeButton("No",
                    DialogInterface.OnClickListener{
                            dialog, id ->
                        dialog.dismiss()
                    })
            builder.create()
        } ?: throw IllegalStateException("El Activity no puede ser null")
    }
}