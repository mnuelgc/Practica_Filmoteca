package es.ua.eps.filmoteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.filmoteca.databinding.ActivityFilmDataBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding

class FilmDataActivity : AppCompatActivity() {
    companion object Extra {
        val EXTRA_FILM_TITLE = "EXTRA_FILM_TITLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFilmDataBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val extraIntent = intent

        val filmTitle = extraIntent.getStringExtra(Extra.EXTRA_FILM_TITLE)

        val filmData = binding.FilmData
        filmData.text =filmTitle

        val buttonRelated = binding.relatedButton
        val buttonEdit = binding.editButton
        val buttonBack = binding.backPrincipalButton


        val intentRelated = Intent(this@FilmDataActivity, FilmDataActivity::class.java)
        val intentEdit = Intent(this@FilmDataActivity, FilmEditActivity::class.java)
        val intentBack = Intent(this@FilmDataActivity, FilmListActivity::class.java)
        intentBack.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        buttonRelated.setOnClickListener {
            val filmTitle = resources.getString(R.string.RelatedFilm)
            intentRelated.putExtra(FilmDataActivity.EXTRA_FILM_TITLE, filmTitle)
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