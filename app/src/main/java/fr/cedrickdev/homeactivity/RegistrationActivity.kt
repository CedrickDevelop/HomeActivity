package fr.cedrickdev.homeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    // *************************AFFICHAGE BOUTON  *****************************
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

    // ************************* BASE DE DONNEE *****************************
    private lateinit var auth : FirebaseAuth
    private var databaseReference : DatabaseReference? = null
    private var database : FirebaseDatabase? = null

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //$$$$$$$$$$$$$$$$$$$$$$$$$$ ON CREATE $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // ************************* AFFICHAGE BOUTON *****************************
        // Dès que l'utilisateur ecrit quelque chose
        RegisterEmailAdress.editText?.doOnTextChanged { text , _, _, _ ->
            emailLiveData.value = text?.toString()
        }
        RegisterPassword.editText?.doOnTextChanged { text , _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(this){ isValid ->
            registerButton.isEnabled = isValid
        }

        // ************************* BASE DE DONNEES *******************
        // Authentification
        auth = FirebaseAuth.getInstance()
        // Database Realtime
        database = FirebaseDatabase.getInstance("https://homeactivity-26613-default-rtdb.europe-west1.firebasedatabase.app")
        databaseReference = database?.reference!!.child("profile")

        register()
    }

    // *************************AFFICHAGE BOUTON *****************************
    // Fonction qui permet de valider le formulaire par vrai ou faux
    private fun validateForm(email: String?, password:String?): Boolean {
        // Pas d'email et password nul ou blanc.
        // Password superieur à 6 caractères
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        return isValidEmail && isValidPassword
    }


    // ************************* CLIC BOUTON *****************************
    private fun register() {
        registerButton.setOnClickListener {
            // Si l'on clic sur le bouton et les champs sont vides alors
                if ( registerFirstName.isEmpty() ){
                    registerFirstName.error = "Ecrivez votre prénom"
                    return@setOnClickListener
                } else if (registerLastName.isEmpty()){
                    registerLastName.error = "Ecrivez votre nom"
                    return@setOnClickListener
                } else if (RegisterPassword.editText.toString().length < 6){
                    RegisterPassword.error = "Votre mot de passe doit contenir 6 charactères minimum"
                    return@setOnClickListener
                }


            auth.createUserWithEmailAndPassword(RegisterEmailAdress.editText?.text.toString(),RegisterPassword.editText?.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        // Enregistrement en database
                        val currentUser = auth.currentUser
                        // enregistrement realtime database  : reference profile // child uid de l'utilisateur
                        val currentUserDb = databaseReference?.child(currentUser?.uid!!)
                        currentUserDb?.child("firstname")?.setValue(registerFirstName.editText?.text.toString())
                        currentUserDb?.child("lastname")?.setValue(registerLastName.editText?.text.toString())

                        //Message pour indiquer la réussite de l'opération
                        Toast.makeText(this@RegistrationActivity, getString(R.string.registrationSucceed), Toast.LENGTH_LONG).show()

                        // On fini cette activité de page et on envoi vers le profile
                        startActivity(Intent(this@RegistrationActivity, AccueilActivity::class.java))
                        finish()


                    } else {
                        //Message pour indiquer l'échec de l'opération
                        Toast.makeText(this@RegistrationActivity, getString(R.string.registrationFailed), Toast.LENGTH_LONG).show()
                    }
                }



        }
    }

}