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
            val directorName = "Joaquim Dos Santos"
            val year = "2023"
            val imdbLink = "http://www.imdb.com/title/tt9362722/"
            val annotation = resources.getString(R.string.SpidermanAnnotations)
         //   val imageName = R.drawable.spiderposter

            intentFilmAData.putExtra(FilmDataActivity.EXTRA_FILM_TITLE, filmTitle)
            intentFilmAData.putExtra(FilmDataActivity.EXTRA_FILM_DIRECTOR, directorName)
            intentFilmAData.putExtra(FilmDataActivity.EXTRA_FILM_YEAR, year)
            intentFilmAData.putExtra(FilmDataActivity.EXTRA_FILM_IMDB, imdbLink)
            intentFilmAData.putExtra(FilmDataActivity.EXTRA_FILM_IMAGE, R.drawable.spiderposter)
            intentFilmAData.putExtra(FilmDataActivity.EXTRA_FILM_IMDB, annotation)

            startActivity(intentFilmAData)
        }

        buttonFilmB.setOnClickListener {
            val filmTitle = resources.getString(R.string.Scarface)
            val directorName ="Brian de Palma"
            val year = "1983"
            val imdbLink = "http://www.imdb.com/title/tt0086250/"
            val annotation = resources.getString(R.string.ScarfaceAnnotations)
           // val imageName = R.drawable.scarface

            intentFilmBData.putExtra(FilmDataActivity.EXTRA_FILM_TITLE, filmTitle)
            intentFilmBData.putExtra(FilmDataActivity.EXTRA_FILM_DIRECTOR, directorName)
            intentFilmBData.putExtra(FilmDataActivity.EXTRA_FILM_YEAR, year)
            intentFilmBData.putExtra(FilmDataActivity.EXTRA_FILM_IMDB, imdbLink)
            intentFilmBData.putExtra(FilmDataActivity.EXTRA_FILM_IMAGE, R.drawable.scarface)
            intentFilmBData.putExtra(FilmDataActivity.EXTRA_FILM_IMDB, annotation)

            startActivity(intentFilmBData)
        }

        buttonAbout.setOnClickListener {
            startActivity(intentAbout)
        }
    }
}