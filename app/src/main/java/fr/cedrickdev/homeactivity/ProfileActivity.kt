package fr.cedrickdev.homeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    // ************************* BASE DE DONNEE *****************************
    private lateinit var auth : FirebaseAuth
    private var databaseReference : DatabaseReference? = null
    private var database : FirebaseDatabase? = null

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //$$$$$$$$$$$$$$$$$$$$$$$$$$ ON CREATE $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // ************************* BASE DE DONNEES *******************
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://homeactivity-26613-default-rtdb.europe-west1.firebasedatabase.app")
        databaseReference = database?.reference!!.child("profile")

        loadProfile()
/*        LoadUserProfil().loadUser()*/

    }

    private fun loadProfile(){
        val user = auth.currentUser
        val userReference = databaseReference?.child(user?.uid!!)

        // Recuperation et affichage de l'email
        emailTextview.text = user?.email

        // Recuperation du prénom et du nom
        userReference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Recuperation et du prénom et du nom
                firstnameTextview.text = snapshot.child("firstname").value.toString()
                nameTextview.text = snapshot.child("lastname").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
        }
    }




}