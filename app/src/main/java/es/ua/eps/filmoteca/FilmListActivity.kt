package es.ua.eps.filmoteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.accessibility.AccessibilityViewCommand.SetTextArguments
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding

class FilmListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFilmListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val buttonFilmA = binding.seeFilmAButton
        val buttonFilmB = binding.seeFilmBButton
        val buttonAbout = binding.aboutButton


        val intentFilmAData = Intent(this@FilmListActivity, FilmDataActivity::class.java)
        val intentFilmBData = Intent(this@FilmListActivity, FilmDataActivity::class.java)
        val intentAbout = Intent(this@FilmListActivity, AboutActivity::class.java)

        buttonFilmA.setOnClickListener {
            val filmTitle = resources.getString(R.string.Spiderman)
            intentFilmAData.putExtra(FilmDataActivity.EXTRA_FILM_TITLE, filmTitle)
            startActivity(intentFilmAData)
        }

        buttonFilmB.setOnClickListener {
            val filmTitle = resources.getString(R.string.Scarface)
            intentFilmBData.putExtra(FilmDataActivity.EXTRA_FILM_TITLE, filmTitle)
            startActivity(intentFilmBData)
        }

        buttonAbout.setOnClickListener {
            startActivity(intentAbout)
        }
    }
}