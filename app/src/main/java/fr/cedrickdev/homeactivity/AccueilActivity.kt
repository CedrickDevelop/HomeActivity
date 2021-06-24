package fr.cedrickdev.homeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class AccueilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)


    }


    // Gestion des boutons du menu du haut
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.topMenuAccount -> AccountTopMenuButtonTouched()
            R.id.topMenuHelp -> HelpTopMenuButtonTouched()
            R.id.topMenuSettings -> SettingsTopMenuButtonTouched()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun AccountTopMenuButtonTouched() {
        println("J'affiche le compte")
        startActivity(Intent(this@AccueilActivity, ProfileActivity::class.java))
    }

    private fun HelpTopMenuButtonTouched() {
        println("J'affiche l'aide'")
        startActivity(Intent(this@AccueilActivity, AideActivity::class.java))
    }

    private fun SettingsTopMenuButtonTouched() {
        println("J'affiche les reglages")
    }




}