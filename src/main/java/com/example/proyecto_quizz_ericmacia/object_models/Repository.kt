package com.example.proyecto_quizz_ericmacia.object_models

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.proyecto_quizz_ericmacia.models.Trivia
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object Repository {

    private val db = FirebaseFirestore.getInstance()
    private const val COLLECTION_NAME = "trivias"

    fun getTrivias(callback: (List<Trivia>) -> Unit) {
        db.collection(COLLECTION_NAME).get()
            .addOnSuccessListener { result ->
                val trivias = result.mapNotNull { it.toObject(Trivia::class.java) }
                callback(trivias)
            }
            .addOnFailureListener { exception ->
                Log.e("Repository", "Error obteniendo datos", exception)
                callback(emptyList())
            }
    }

    suspend fun addTrivia(context: Context, trivia: Trivia): Boolean {
        return try {
            val documentRef = db.collection(COLLECTION_NAME).add(trivia).await()
            val triviaId = documentRef.id
            documentRef.update("id", triviaId).await()
            Toast.makeText(context, "Trivia añadida correctamente", Toast.LENGTH_SHORT).show()
            true
        } catch (e: Exception) {
            Log.e("Repository", "Error añadiendo", e)
            Toast.makeText(context, "Error al añadir la trivia", Toast.LENGTH_SHORT).show()
            false
        }
    }

    suspend fun updateTrivia(context: Context, trivia: Trivia): Boolean {
        return try {
            if (trivia.id.isNullOrBlank()) {
                Log.e("Repository", "Error: ID de trivia es nulo o vacío")
                Toast.makeText(context, "Error: ID de la trivia inválido", Toast.LENGTH_SHORT).show()
                return false
            }
            db.collection(COLLECTION_NAME).document(trivia.id).set(trivia).await()
            Toast.makeText(context, "Trivia actualizada correctamente", Toast.LENGTH_SHORT).show()
            true
        } catch (e: Exception) {
            Log.e("Repository", "Error actualizando ${trivia}", e)
            Toast.makeText(context, "Error al actualizar la trivia", Toast.LENGTH_SHORT).show()
            false
        }
    }

    suspend fun deleteTrivia(context: Context, id: String): Boolean {
        return try {
            if (id.isNotBlank()) {
                db.collection(COLLECTION_NAME).document(id).delete().await()
                Toast.makeText(context, "Trivia eliminada correctamente", Toast.LENGTH_SHORT).show()
                true
            } else {
                Log.e("Repository", "ID de la trivia no es válido para eliminar")
                Toast.makeText(context, "Error: ID de la trivia inválido", Toast.LENGTH_SHORT).show()
                false
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error eliminando trivia", e)
            Toast.makeText(context, "Error al eliminar la trivia", Toast.LENGTH_SHORT).show()
            false
        }
    }
}
