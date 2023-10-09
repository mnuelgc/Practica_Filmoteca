package es.ua.eps.filmoteca

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.accessibility.AccessibilityViewCommand.SetTextArguments
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.filmoteca.databinding.ActivityFilmEditBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmRecylceviewListBinding


class FilmListActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var adapter: RecyclerView.Adapter<*>? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    companion object{
        lateinit var res : Resources
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        res = resources

        if (resources.getBoolean(R.bool.ListView) && !resources.getBoolean(R.bool.RecycledView)) {
            val binding = ActivityFilmListBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val list = binding.list
            val films = mutableListOf<Film>()
            for (film: Film in FilmDataSource.films){
                films.add(film)
            }

            val adapter = FilmsAdapter(
                this,
                R.layout.item_film, FilmDataSource.films
            )

            list.adapter = adapter

            val intentFilm = Intent(this@FilmListActivity, FilmDataActivity::class.java)

            list.setOnItemClickListener { parent: AdapterView<*>, view: View,
                                          position: Int, id: Long ->

                intentFilm.putExtra(FilmDataActivity.EXTRA_FILM_ID, position)
                startActivity(intentFilm)
            }
        }else if ((!resources.getBoolean(R.bool.ListView) && resources.getBoolean(R.bool.RecycledView)))
        {
            val binding = ActivityFilmRecylceviewListBinding.inflate(layoutInflater)
            setContentView(binding.root)

            recyclerView = binding.filmsList

            layoutManager = LinearLayoutManager(this)
            recyclerView?.layoutManager = layoutManager

            val adapter = FilmRecycledViewListAdapter(FilmDataSource.films, this)

            recyclerView?.adapter = adapter
            this.adapter = adapter

            adapter.setOnItemCLickListener { position ->
                val intentFilm = Intent(this@FilmListActivity, FilmDataActivity::class.java)
                intentFilm.putExtra(FilmDataActivity.EXTRA_FILM_ID, position)
                startActivity(intentFilm)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_principal,menu)
        return true
    }
}