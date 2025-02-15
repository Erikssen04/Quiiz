package com.example.proyecto_quizz_ericmacia.features.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyecto_quizz_ericmacia.R
import com.example.proyecto_quizz_ericmacia.databinding.ActivityMainMenuBinding
import com.example.proyecto_quizz_ericmacia.features.auth.LoginActivity
import com.example.proyecto_quizz_ericmacia.fragments.MainMenuFragment
import com.google.firebase.auth.FirebaseAuth

class MainMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainMenuBinding
    lateinit var fragmentMainMenu: MainMenuFragment
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializar el binding
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.appBarLayoutDrawer.toolbar
        setSupportActionBar(toolbar)
        toolbar.apply {
            title = "Quiiz: App de Trivias" // Título de la app
            setTitleTextAppearance(context, R.style.ToolbarTitleStyle)
            setBackgroundColor(getColor(R.color.drawer_background))
            setTitleTextColor(getColor(R.color.yellow_quiz))
        }
        // Obtener el NavHostFragment desde el FragmentContainerView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment

        // Obtener el NavController desde el NavHostFragment
        navController = navHostFragment.navController

        val navView = binding.myNavView

        // Configuración para gestionar la navegación entre fragmentos
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragment_main_menu, R.id.fragment_baul_trivias),
            binding.mainMenu
        )
        // Configurar ActionBar para manejar la navegación
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Vincular el NavView con el NavController
        navView.setupWithNavController(navController)

        binding.myNavView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    cerrarSesion()
                    true
                }
                else -> {
                    // Manejar navegación normal
                    menuItem.isChecked = false
                    navController.navigate(menuItem.itemId)
                    binding.mainMenu.closeDrawers()
                    true
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Método para gestionar la navegación hacia arriba
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_op, menu)
        return true
    }

    //Navegación del menú de opciones.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fragment_main_menu -> {
                navController.navigate(R.id.fragment_main_menu) // Navegar al menú principal
                true
            }
            R.id.fragment_baul_trivias -> {
                navController.navigate(R.id.fragment_baul_trivias) // Navegar al fragmento del baúl de trivias
                true
            }
            R.id.logout -> { // Cerrar sesión
                cerrarSesion()
                true
            }
            else -> false //super.onOptionsItemSelected(item)
        }
    }

    private fun cerrarSesion() {

        val sharedpreferences= getSharedPreferences(LoginActivity.Global.preferencias_compartidas, Context.MODE_PRIVATE).edit()
        sharedpreferences.clear()
        sharedpreferences.apply()

        // Cerrar sesión en Firebase
        FirebaseAuth.getInstance().signOut()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
         finishAffinity()
    }

}
