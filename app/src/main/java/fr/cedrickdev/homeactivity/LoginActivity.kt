package fr.cedrickdev.homeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Instancie la base de donnée
        auth = FirebaseAuth.getInstance()

        // Si l'utilisateur existe deja on l'envoi au profil
        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
            finish()
        }

        // On appelle la fonction de connexion
        login()
    }

    private fun login(){
        val emailLayout: TextInputLayout = findViewById(R.id.LoginEmailAdress)
        val passwordLayout: TextInputLayout = findViewById(R.id.LoginPassword)

        // Le bouton n'apparait pas mais si il y a un probleme
        loginButton.setOnClickListener {
            if(emailLayout.editText?.text.toString().isEmpty()){
                emailLayout.error = "Veuillez entrer votre email"
                return@setOnClickListener
            } else if (passwordLayout.editText?.text.toString().isEmpty()){
                emailLayout.error = "Veuillez entrer votre mot de passe"
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(emailLayout.editText?.text.toString(), passwordLayout.editText?.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
                        finish()
                    } else {
                        //Message pour indiquer l'échec de l'opération
                        Toast.makeText(this@LoginActivity, getString(R.string.loginFailed), Toast.LENGTH_LONG).show()
                    }
                }
        }

        RegisterTextView.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }
    }
}