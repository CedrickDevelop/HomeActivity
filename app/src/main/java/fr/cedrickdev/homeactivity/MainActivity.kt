package fr.cedrickdev.homeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* LoginButtonTouched()
        RegisterButtonTouched()*/
    }

    // Le Bouton Inscription
    fun registrationButtonTouched(button: View){
        val ouverture = Intent(this@MainActivity, RegistrationActivity::class.java)
        startActivity(ouverture)
    }

    // Le Bouton Connexion
    fun loginButtonTouched(button: View){
        val ouverture = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(ouverture)
    }


/*    fun LoginButtonTouched(){
        val buttonLogin: Button = findViewById(R.id.loginBtn)
        buttonLogin.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }

    fun RegisterButtonTouched(){
        val buttonRegister: Button = findViewById(R.id.registrationButton)
        buttonRegister.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))
            finish()
        }
    }*/

}