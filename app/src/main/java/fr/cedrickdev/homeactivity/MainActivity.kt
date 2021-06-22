package fr.cedrickdev.homeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    // Le bouton Google
    fun googleBtnTouched(button : View){
        // Ouverture de la page de google connexion
        val ouverture = Intent(this, LoginActivity::class.java)
        startActivity(ouverture)
    }

    fun registrationButtonTouched(button: View){
        val ouverture = Intent(this, RegistrationActivity::class.java)
        startActivity(ouverture)
    }
}