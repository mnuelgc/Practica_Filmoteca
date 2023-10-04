package es.ua.eps.filmoteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, FilmDataSource().films
        )
        list.adapter = adapter
    }
}