package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
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
    }

    private val startForResult = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                        result : ActivityResult -> onActivityResult(MOVIE_RESULT, result.resultCode, result.data)}

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
                val filmData = findViewById<TextView>(R.id.FilmData)
                filmData.text = "${filmData.text} [${resources.getString(R.string.Edited)}]"
            }
        }
    }
}
