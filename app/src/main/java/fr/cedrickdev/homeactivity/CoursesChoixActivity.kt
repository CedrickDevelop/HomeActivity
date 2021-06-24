package fr.cedrickdev.homeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_courses_choix.*
import kotlinx.android.synthetic.main.activity_main.*

class CoursesChoixActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses_choix)
        btnNouvelleTacheTouched()
    }


    private fun btnNouvelleTacheTouched() {
        btn_nouvelleTache.setOnClickListener {
            // Controller la navigation entre les différents écrans
            startActivity(Intent(this@CoursesChoixActivity, CoursesListeActivity::class.java))
            finish()
        }
    }
}
