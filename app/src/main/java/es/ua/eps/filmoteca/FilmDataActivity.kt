package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import es.ua.eps.filmoteca.databinding.ActivityFilmDataBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding

class FilmDataActivity : AppCompatActivity() {

    private val MOVIE_RESULT = 1

    companion object Extra {
        const val EXTRA_FILM_ID  ="EXTRA_FILM_ID"
    }

    private val startForResult = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                        result : ActivityResult -> onActivityResult(MOVIE_RESULT, result.resultCode, result.data)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFilmDataBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val extraIntent = intent

        val position  = extraIntent.getIntExtra(EXTRA_FILM_ID, 0)
        val film : Film = FilmDataSource().films[position]


        val filmData = binding.filmData
        filmData.text = film.title


        val image = binding.imgFilm
        val directorName = binding.directorName
        val year = binding.yearValue
        val genreAndFormat = binding.genreFormat
        val annotation = binding.annotations

        val genresArr = resources.getStringArray(R.array.Genres)

        val posGenre = film.genre as Int
        val genre = genresArr[posGenre]

        val formatArr = resources.getStringArray(R.array.Formats)
        val posFormat = film.format as Int

        val format = formatArr[posFormat]

        genreAndFormat.text =  "$genre $format"
        annotation.text = film.comments

        image.setImageResource(film.imagesResId)

        directorName.text = film.director
        year.text = film.year.toString()

        val buttonIMBD = binding.IMDBButton
        val buttonEdit = binding.editButton
        val buttonBack = binding.backPrincipalButton



        val intentEdit = Intent(this@FilmDataActivity, FilmEditActivity::class.java)
        val intentBack = Intent(this@FilmDataActivity, FilmListActivity::class.java)

        intentBack.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(film.imdbUrl))

        buttonIMBD.setOnClickListener {
            if (viewIntent.resolveActivity(packageManager) != null) {
                startActivity(viewIntent)
            }
        }

        buttonEdit.setOnClickListener {
            intentEdit.putExtra(FilmEditActivity.Extra.EXTRA_FILM_ID, film.imagesResId)

            if (Build.VERSION.SDK_INT >= 30)
            {
                startForResult.launch(intentEdit)
            }
            else {
                @Suppress("DEPRECATION")
                startActivityForResult(intentEdit,MOVIE_RESULT)
            }
        }

        buttonBack.setOnClickListener {
            startActivity(intentBack)
        }
    }

    /*What we do when subactivity ends*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       @Suppress ("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode)
        {
            MOVIE_RESULT -> if (resultCode == Activity.RESULT_OK)
            {
                val filmData = findViewById<TextView>(R.id.filmData)
                filmData.text = "${filmData.text} [${resources.getString(R.string.Edited)}]"
            }
        }
    }
}
