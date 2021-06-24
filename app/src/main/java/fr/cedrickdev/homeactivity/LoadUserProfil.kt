package fr.cedrickdev.homeactivity

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*

class LoadUserProfil {

  /*  private lateinit var auth : FirebaseAuth*/
    /*private var databaseReference : DatabaseReference? = null
     database : FirebaseDatabase? = null*/
/*
    private var auth = FirebaseAuth.getInstance()
    private var database = FirebaseDatabase.getInstance("https://homeactivity-26613-default-rtdb.europe-west1.firebasedatabase.app")
    private var databaseReference = database?.reference!!.child("profile")

    val user = auth.currentUser
    val userReference = databaseReference?.child(user?.uid!!)


    fun loadUser(){

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
    }*/

}
