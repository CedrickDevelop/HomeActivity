package fr.cedrickdev.homeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // L'utilisateur existe deja ?
        UserExist()

        // Lien Enregistrer un nouveau compte
        Register()

        // Lien pour accéder à la connexion
        Login()
    }

    private fun UserExist() {

        // Instancie la base de donnée
        val auth : FirebaseAuth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
            finish()
        }
    }


    private fun Register() {
        MainRegisterTextView.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))
        }
    }

    private fun Login() {
        loginBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
    }

}