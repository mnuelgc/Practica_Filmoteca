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
        val EXTRA_FILM_TITLE = "EXTRA_FILM_TITLE"
        val EXTRA_FILM_DIRECTOR = "EXTRA_FILM_DIRECTOR"
        val EXTRA_FILM_YEAR = "EXTRA_FILM_YEAR"
        val EXTRA_FILM_IMDB = "EXTRA_FILM_IMDB"
        val EXTRA_FILM_IMAGE = "EXTRA_FILM_IMAGE"
        val EXTRA_FILM_ANNOTATIONS = "EXTRA_FILM_ANNOTATIONS"
    }

    private val startForResult = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                        result : ActivityResult -> onActivityResult(MOVIE_RESULT, result.resultCode, result.data)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFilmDataBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val extraIntent = intent

        val filmTitle = extraIntent?.getStringExtra(EXTRA_FILM_TITLE)

        val filmData = binding.filmData
        filmData.text =filmTitle

        val image = binding.imgFilm
        val directorName = binding.directorName
        val year = binding.yearValue

        val annotation = binding.annotations

        annotation.text = extraIntent?.getStringExtra(EXTRA_FILM_ANNOTATIONS)

        val img = extraIntent.getIntExtra(EXTRA_FILM_IMAGE, R.drawable.ic_launcher_foreground)
        image.setImageResource(img)
        directorName.text = extraIntent?.getStringExtra(EXTRA_FILM_DIRECTOR)
        year.text = extraIntent?.getStringExtra(EXTRA_FILM_YEAR)

        val buttonIMBD = binding.IMDBButton
        val buttonEdit = binding.editButton
        val buttonBack = binding.backPrincipalButton



        val intentEdit = Intent(this@FilmDataActivity, FilmEditActivity::class.java)
        val intentBack = Intent(this@FilmDataActivity, FilmListActivity::class.java)

        intentBack.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(extraIntent?.getStringExtra(EXTRA_FILM_IMDB)))

        buttonIMBD.setOnClickListener {
            if (viewIntent.resolveActivity(packageManager) != null) {
                startActivity(viewIntent)
            }
        }

        buttonEdit.setOnClickListener {
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
