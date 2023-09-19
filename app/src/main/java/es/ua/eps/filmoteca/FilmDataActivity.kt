package es.ua.eps.filmoteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.filmoteca.databinding.ActivityFilmDataBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding

class FilmDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFilmDataBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val buttonRelated = binding.relatedButton
        val buttonEdit = binding.editButton
        val buttonBack = binding.backPrincipalButton


        val intentRelated = Intent(this@FilmDataActivity, FilmDataActivity::class.java)
        val intentEdit = Intent(this@FilmDataActivity, FilmEditActivity::class.java)
        val intentBack = Intent(this@FilmDataActivity, FilmListActivity::class.java)
        intentBack.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        buttonRelated.setOnClickListener {
            startActivity(intentRelated)
        }

        buttonEdit.setOnClickListener {
            startActivity(intentEdit)
        }

        buttonBack.setOnClickListener {
            startActivity(intentBack)
        }
    }
}