package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.filmoteca.databinding.ActivityFilmEditBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding

class FilmEditActivity : AppCompatActivity() {

    companion object Extra {
        const val EXTRA_FILM_ID = "EXTRA_FILM_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFilmEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val extraIntent = intent
        val position  = extraIntent.getIntExtra(FilmDataActivity.EXTRA_FILM_ID, 0)
        val film : Film = FilmDataSource().films[position]

        val buttonSave = binding.saveButton
        val buttonCancel = binding.cancelButton


        val imgfield = binding.imgfilm
        imgfield.setImageResource(film.imagesResId)



        buttonSave.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}