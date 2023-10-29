package es.eps.ua.drawable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import es.eps.ua.drawable.databinding.ActivityMainBinding
import es.eps.ua.drawable.databinding.ActivityPantallaBinding

class PantallaActivity : AppCompatActivity() {

    private lateinit var viewBinding : ActivityPantallaBinding

    private lateinit var buttonExc1 : Button
    private lateinit var buttonExc2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPantallaBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        buttonExc1 = viewBinding.Exercice1
        buttonExc2 = viewBinding.Exercice2

        buttonExc1.setOnClickListener {
            val intent = Intent(this@PantallaActivity, PantallaExercice1Activity::class.java)
            startActivity(intent)
        }

    }
}