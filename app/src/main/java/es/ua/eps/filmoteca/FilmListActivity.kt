package es.ua.eps.filmoteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.view.accessibility.AccessibilityViewCommand.SetTextArguments
import es.ua.eps.filmoteca.databinding.ActivityFilmEditBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding


class FilmListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFilmListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val list = binding.list
        val films = mutableListOf<Film>()
        for (film: Film in FilmDataSource().films){
            films.add(film)
        }

        val adapter = FilmsAdapter(
            this,
            R.layout.item_film, FilmDataSource().films
        )



        list.adapter = adapter
    }
}