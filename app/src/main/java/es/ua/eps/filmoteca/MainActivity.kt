package es.ua.eps.filmoteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import es.ua.eps.filmoteca.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FilmListFragment.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) return

        // Comprueba si estamos usando el layout dinámico
        if (findViewById<View?>(R.id.fragment_container) != null) {
            // Si se está restaurando, no hace falta cargar el fragmento
            if (savedInstanceState != null) return

            // Creamos el fragmento
            val listFragment = FilmListFragment()

            // Pasamos los extras del intent al fragmento
            listFragment.arguments = intent.extras

            // Añadimos el fragmento al contenedor
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, listFragment).commit()
        }
    }

    override fun onItemSelected(position: Int) {
        var dataFragmet = supportFragmentManager
            .findFragmentById(R.id.film_data_fragment) as FilmDataFragment?

        if (dataFragmet != null) {
            dataFragmet.setFilmItem(position)
        } else {
            dataFragmet = FilmDataFragment()
            val args = Bundle()
            args.putInt(FilmDataFragment.PARAM_POSICION, position)
            dataFragmet.arguments = args

            val t = supportFragmentManager.beginTransaction()
            t.replace(R.id.fragment_container, dataFragmet)
            t.addToBackStack(null)
            t.commit()
        }
    }
}