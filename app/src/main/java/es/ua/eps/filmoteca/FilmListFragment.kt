package es.ua.eps.filmoteca

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ListView
import androidx.fragment.app.ListFragment
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding
import es.ua.eps.filmoteca.databinding.FragmentFilmDataBinding
import es.ua.eps.filmoteca.databinding.FragmentFilmListBinding
import kotlin.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FilmListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilmListFragment : ListFragment() {
    var callback : OnItemSelectedListener? = null
    public lateinit var adapter : FilmsAdapter


    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        val binding = FragmentFilmListBinding.inflate(layoutInflater)
        val list = binding.list
        registerForContextMenu(list)

        adapter = FilmsAdapter(
            cont,
            R.layout.item_film, FilmDataSource.films
        )

        list.adapter = adapter


        return binding.root
    }

    override fun onListItemClick(l: ListView, v: View,
                                 position: Int, id: Long) {
        callback?.onItemSelected(position)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = try {
            context as OnItemSelectedListener
        } catch (e: ClassCastException) {
            throw  ClassCastException(context.toString() + "debe implementar OnItemSelectedListener")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FilmListFragment.
         */
        lateinit var res : Resources
        lateinit var cont : Context


        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FilmListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.menu_principal,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId){
            R.id.addFilm ->{
                val film = Film(null)
                FilmDataSource.films.add(film)

                adapter.notifyDataSetChanged()

                return true
            }
            R.id.about ->{
                val intent = Intent(activity, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return false
    }
}