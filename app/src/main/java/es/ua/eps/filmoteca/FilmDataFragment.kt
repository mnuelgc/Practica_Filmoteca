package es.ua.eps.filmoteca

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import es.ua.eps.filmoteca.databinding.FragmentFilmDataBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FilmDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilmDataFragment : Fragment() {

    var filmData: TextView? = null
    var image: ImageView? = null
    var directorName: TextView? = null
    var year: TextView? = null
    var genreAndFormat: TextView? = null
    var annotation: TextView? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        res = resources
        cont = requireContext()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        val binding = FragmentFilmDataBinding.inflate(layoutInflater)
        val view = binding.root
       // activity?.setContentView(view)

/*
           supportActionBar?.setHomeButtonEnabled(true)
           supportActionBar?.setDisplayHomeAsUpEnabled(true)
*/
        val extraIntent = activity?.intent

        //val position  = extraIntent?.getIntExtra(FilmDataActivity.EXTRA_FILM_ID, 0)
        val position : Int = 1
        val film : Film = Film(cont)



        /*    val f = Film()
            f.title = res.getString(R.string.Scarface)
            f.director = "Brian de Palma"
            f.imagesResId = R.drawable.scarface
            f.comments  =  res.getString(R.string.ScarfaceAnnotations)
            f.format = Film.Companion.FORMAT_DVD
            f.genre = Film.Companion.FORMAT_ACTION
            f.imdbUrl  ="http://www.imdb.com/title/tt9362722"
            f.year = 1983
            f.convertImageDrawableToBitmap(requireContext())
            // FilmDataSource.films.add(f)
    */

        bindElements(binding)
        SetFilmData(film)

        val buttonIMBD = binding.IMDBButton
        val buttonEdit = binding.editButton
        val buttonBack = binding.backPrincipalButton



        /*
                val intentEdit = Intent(this@FilmDataActivity, FilmEditActivity::class.java)
                val intentBack = Intent(this@FilmDataActivity, FilmListActivity::class.java)

                intentBack.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        */

        buttonIMBD.setOnClickListener {
            /*        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(film.imdbUrl))

                    if (viewIntent.resolveActivity(packageManager) != null) {
                        startActivity(viewIntent)
                    }
             */
        }

        buttonEdit.setOnClickListener {
            /*       intentEdit.putExtra(FilmEditActivity.EXTRA_FILM_ID, position)

                   if (Build.VERSION.SDK_INT >= 30)
                   {
                       startForResult.launch(intentEdit)
                   }
                   else {
                       @Suppress("DEPRECATION")
                       startActivityForResult(intentEdit,MOVIE_RESULT)
                   }

             */
        }

        buttonBack.setOnClickListener {
            //     startActivity(intentBack)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FilmDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        lateinit var res : Resources
        lateinit var cont : Context
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FilmDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun bindElements(binding: FragmentFilmDataBinding)
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