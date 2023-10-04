package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.filmoteca.databinding.ActivityFilmEditBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding

class FilmEditActivity : AppCompatActivity() {

    companion object Extra {
        const val EXTRA_FILM_IMAGE = "EXTRA_FILM_IMAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFilmEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val buttonSave = binding.saveButton
        val buttonCancel = binding.cancelButton
        val extraIntent = intent


        val imgfield = binding.imgfilm
        val img = extraIntent.getIntExtra(FilmEditActivity.EXTRA_FILM_IMAGE, R.drawable.ic_launcher_foreground)
        imgfield.setImageResource(img)



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