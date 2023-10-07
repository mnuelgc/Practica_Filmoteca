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

        val film : Film = FilmDataSource.films[position]

        val buttonSave = binding.saveButton
        val buttonCancel = binding.cancelButton


        val imgfield = binding.imgfilm
        imgfield.setImageResource(film.imagesResId)


        val titleField = binding.filmTitle
        val directorField = binding.filmDirector
        val yearField = binding.filmYear
        val imdbUrlField = binding.filmIMDBLink
        val genreField = binding.filmGenre
        val formatField = binding.filmFormat
        val commentsField = binding.filmAnnotation



         titleField.setText(FilmDataSource.films[position].title)
         directorField.setText(FilmDataSource.films[position].director)
         yearField.setText(FilmDataSource.films[position].year.toString())
         imdbUrlField.setText(FilmDataSource.films[position].imdbUrl)
         genreField.setSelection(FilmDataSource.films[position].genre)
         formatField.setSelection(FilmDataSource.films[position].format)
         commentsField.setText(FilmDataSource.films[position].comments)


        buttonSave.setOnClickListener {
            FilmDataSource.films[position].title = titleField.text.toString()
            FilmDataSource.films[position].director = directorField.text.toString()
            when(binding?.filmYear?.text?.toString().isNullOrEmpty())
            {
                true -> FilmDataSource.films[position].year = 0
                false -> FilmDataSource.films[position].year = yearField.text.toString().toString().toInt()
            }
            FilmDataSource.films[position].imdbUrl = imdbUrlField.text.toString()
            FilmDataSource.films[position].genre = genreField.selectedItemPosition
            FilmDataSource.films[position].format = formatField.selectedItemPosition
            FilmDataSource.films[position].comments = commentsField.text.toString()


            setResult(Activity.RESULT_OK)
            finish()
        }

        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}