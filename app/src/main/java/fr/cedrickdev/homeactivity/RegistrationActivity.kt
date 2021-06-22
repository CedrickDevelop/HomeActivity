package fr.cedrickdev.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout

class RegistrationActivity : AppCompatActivity() {

    // Constantes de base : email, mot de passe, validite mot de passe en lambda
    private val emailLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        // On desactive les connections automatiques
        this.value = false

        // On recupere le password et email pour tester
        addSource(emailLiveData){ email ->
            val password = passwordLiveData.value
            this.value = validateForm(email, password)
        }

        addSource(passwordLiveData){ password ->
            val email = emailLiveData.value
            this.value = validateForm(email, password)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val emailLayout = findViewById<TextInputLayout>(R.id.SignInEmailAdress)
        val passwordLayout = findViewById<TextInputLayout>(R.id.SignInPassword)
        val signInButton = findViewById<Button>(R.id.SignInButton)


        // Dès que l'utilisateur ecrit quelque chose
        emailLayout.editText?.doOnTextChanged { text , _, _, _ ->
            emailLiveData.value = text?.toString()
        }
        passwordLayout.editText?.doOnTextChanged { text , _, _, _ ->
            passwordLiveData.value = text?.toString()
        }

        isValidLiveData.observe(this){ isValid ->
            signInButton.isEnabled = isValid
        }
    }


    // Fonction qui permet de valider le formulaire par vrai ou faux
    private fun validateForm(email: String?, password:String?): Boolean? {
        // Pas d'email et password nul ou blanc.
        // Password superieur à 6 caractères
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        return isValidEmail && isValidPassword
    }
}