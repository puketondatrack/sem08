package com.sem08

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sem08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    //Objeto de firebase auth
    private lateinit var auth : FirebaseAuth

    //Pantalla xml
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Iniciar Auth
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Click Registro
        binding.btRegistro.setOnClickListener{registrar()}

        //Click login
        binding.btLogin.setOnClickListener{login()}
    }

    private fun registrar(){
        val email = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()


        //Registro de usuario
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    cargarPantalla(user)
                }
                else{
                    Toast.makeText(baseContext, "Fallo ${task.exception.toString()}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun cargarPantalla(user : FirebaseUser?){
        if (user != null){
            val intent = Intent(this,Principal::class.java)
            startActivity(intent)
        }
    }


    private fun login(){
        val email = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        //Login
        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener{result ->
                if (result.isSuccessful){
                    val user = auth.currentUser
                    cargarPantalla(user)
                }
                else{
                    Toast.makeText(baseContext, getText(R.string.noLogin), Toast.LENGTH_LONG).show()
                }

                }

    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        cargarPantalla(user)
    }
}