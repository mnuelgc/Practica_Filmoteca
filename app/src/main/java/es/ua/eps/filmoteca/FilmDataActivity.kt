package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import es.ua.eps.filmoteca.databinding.ActivityFilmDataBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding

class FilmDataActivity : AppCompatActivity() {


    var filmData: TextView? = null
    var image: ImageView? = null
    var directorName: TextView? = null
    var year: TextView? = null
    var genreAndFormat: TextView? = null
    var annotation: TextView? = null

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
        val film : Film = FilmDataSource.films[position]

        bindElements(binding)
        SetFilmData(film)

        val buttonIMBD = binding.IMDBButton
        val buttonEdit = binding.editButton
        val buttonBack = binding.backPrincipalButton




        val intentEdit = Intent(this@FilmDataActivity, FilmEditActivity::class.java)
        val intentBack = Intent(this@FilmDataActivity, FilmListActivity::class.java)

        intentBack.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP


        buttonIMBD.setOnClickListener {
            val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(film.imdbUrl))

            if (viewIntent.resolveActivity(packageManager) != null) {
                startActivity(viewIntent)
            }
        }

        buttonEdit.setOnClickListener {
            intentEdit.putExtra(FilmEditActivity.EXTRA_FILM_ID, position)

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
                val extraIntent = intent
                val position  = extraIntent.getIntExtra(EXTRA_FILM_ID, 0)
                val film : Film = FilmDataSource.films[position]

                SetFilmData(film)
            }
        }
    }

    private fun bindElements(binding: ActivityFilmDataBinding)
    {
        filmData = binding.filmData
        image = binding.imgFilm
        directorName = binding.directorName
        year = binding.yearValue
        genreAndFormat = binding.genreFormat
        annotation = binding.annotations
    }

    private fun SetFilmData(film: Film)
    {

        filmData?.text = film.title

        val genresArr = resources.getStringArray(R.array.Genres)
        val posGenre = film.genre as Int
        val genre = genresArr!![posGenre!!]
        val formatArr = resources.getStringArray(R.array.Formats)
        val posFormat = film.format as Int
        val format = formatArr[posFormat!!]

        genreAndFormat?.text =  "$genre $format"
        annotation?.text = film.comments

        image?.setImageBitmap(film.imageBitmap)

        directorName?.text = film.director
        year?.text = film.year.toString()

    }
}
