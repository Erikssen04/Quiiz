package com.example.proyecto_quizz_ericmacia.controller

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_quizz_ericmacia.adapter.AdapterTrivia
import com.example.proyecto_quizz_ericmacia.dao.TriviaDAOimp
import com.example.proyecto_quizz_ericmacia.databinding.ActivityBaulTriviasBinding
import com.example.proyecto_quizz_ericmacia.dialogues.DialogDeleteTrivia
import com.example.proyecto_quizz_ericmacia.dialogues.DialogEditTrivia
import com.example.proyecto_quizz_ericmacia.dialogues.DialogNewTrivia
import com.example.proyecto_quizz_ericmacia.features.ui.BaulTriviasActivity
import com.example.proyecto_quizz_ericmacia.models.Trivia

class Controller (val context: Context, val binding: ActivityBaulTriviasBinding) {
    lateinit var adapterTrivia: AdapterTrivia

    lateinit var layoutManager: LinearLayoutManager
    lateinit var listTrivias : MutableList<Trivia> //lista de objetos Trivia
    init {
        initData()
    }
    fun initData(){
        setScrollWithOffsetLinearLayout()
        listTrivias = TriviaDAOimp. myDao.getDataTrivias(). toMutableList() //llamamos al singleton DAO.
        initOnClickListener()
    }

    private fun setScrollWithOffsetLinearLayout(){
        layoutManager = (context as BaulTriviasActivity)
            .binding.myRecyclerView.layoutManager as LinearLayoutManager
    }

    private fun initOnClickListener(){
        val myActivity = context as BaulTriviasActivity
        myActivity.binding.btnAdd.setOnClickListener {
            addTrivia()
        }
    }


    fun loggOut() {
        Toast.makeText( context, "He mostrado los datos en pantalla", Toast. LENGTH_LONG).show()
        listTrivias.forEach{
            println (it)
        }
    }
    fun setAdapter() {
        val myActivity = context as BaulTriviasActivity

        adapterTrivia = AdapterTrivia(
            listTrivias,
            {
                    pos -> delTrivia(pos)
            },
            {
                    pos -> updateTrivia(pos)
            }
        )
        myActivity.binding.myRecyclerView.adapter = adapterTrivia
    }

    fun updateTrivia(pos: Int){
        val editDialog = DialogEditTrivia(listTrivias.get(pos)){
                editTrivia -> okOnEditContext(editTrivia, pos)
        }
        val myActivity = context as BaulTriviasActivity
        editDialog.show(myActivity.supportFragmentManager, "Editamos una Trivia")
    }

    fun addTrivia(){
        Toast.makeText(context, "Añadiremos una nueva Trivia", Toast.LENGTH_LONG).show()
        val dialog = DialogNewTrivia(){
                trivia -> okOnNewTrivia(trivia)
        }
        val myActivity = context as BaulTriviasActivity
        dialog.show(myActivity.supportFragmentManager, "Añadimos una nueva Trivia")
    }

    fun delTrivia(pos : Int){
        val dialog = DialogDeleteTrivia(
            pos,
            listTrivias.get(pos).title
        ){
                position -> okOnDeleteTrivia(position)
        }
        val myActivity = context as BaulTriviasActivity
        dialog.show(myActivity.supportFragmentManager, "Borraremos la trivia de posición $pos")
    }

    private fun okOnDeleteTrivia(pos: Int){
        listTrivias.removeAt(pos)
        adapterTrivia.notifyItemRemoved(pos)
        Toast.makeText(context, "Trivia borrada", Toast.LENGTH_LONG).show()
    }

    private fun okOnNewTrivia(trivia: Trivia){
        listTrivias.add(listTrivias.size, trivia)
        adapterTrivia.notifyItemInserted(listTrivias.lastIndex)
        layoutManager.scrollToPositionWithOffset(listTrivias.lastIndex, 20)
    }

    private fun okOnEditContext(editTrivia: Trivia, pos: Int){
        listTrivias.removeAt(pos)
        adapterTrivia.notifyItemRemoved(pos)
        listTrivias.add(pos, editTrivia)
        adapterTrivia.notifyItemInserted(pos)
        layoutManager.scrollToPositionWithOffset(pos, 20)
    }
}