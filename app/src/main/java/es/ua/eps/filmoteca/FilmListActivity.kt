package es.ua.eps.filmoteca

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.ActionMode
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView.MultiChoiceModeListener
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import androidx.core.view.children
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.filmoteca.databinding.ActivityFilmListBinding
import es.ua.eps.filmoteca.databinding.ActivityFilmRecylceviewListBinding


class FilmListActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var adapterRV: RecyclerView.Adapter<*>? = null
    var adapterFA: FilmsAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    companion object{
        lateinit var res : Resources
    }

    private  val actionModeCallback : ActionMode.Callback = object :ActionMode.Callback{
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val inflater = mode?.menuInflater
            inflater?.inflate(R.menu.menu_contextual_multiple,menu)
            val numElement = layoutManager!!.itemCount
            for (position :Int in 0 ..< numElement) {
                layoutManager?.getChildAt(position)
                    ?.findViewById<CheckBox>(R.id.item_check)?.visibility = View.VISIBLE
            }
            return  true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {

            return false
        }
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.deleteFilms -> {

                    val listOfIndexToRemove = ArrayList<Int>()

                    for (position: Int in 0..<layoutManager!!.childCount) {
                        var isChecked = false
                        isChecked = layoutManager!!.getChildAt(position)!!
                            .findViewById<CheckBox>(R.id.item_check).isChecked

                        if (isChecked)
                            listOfIndexToRemove.add(position)
                    }

                    listOfIndexToRemove.sortDescending()

                    for (position: Int in listOfIndexToRemove) {
                        val lastPositon = layoutManager!!.childCount - 1
                        val checkBox = layoutManager!!.getChildAt(position)!!
                            .findViewById<CheckBox>(R.id.item_check)

                        if (checkBox.isChecked) {
                            checkBox.isChecked = false
                            checkBox.visibility = View.INVISIBLE
                            FilmDataSource.films.removeAt(position)
                            adapterRV?.notifyItemRemoved(position)
                        }
                    }
                    listOfIndexToRemove.clear()
                    return true
                }

                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            (adapterRV as FilmRecycledViewListAdapter).actionMode = null

            val itemsRemain = layoutManager!!.childCount

            for (position : Int in 0..<itemsRemain)
            {
                val checkBox = layoutManager!!.getChildAt(position)!!.findViewById<CheckBox>(R.id.item_check)
                checkBox.isChecked = false
                checkBox.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        res = resources

        if (resources.getBoolean(R.bool.ListView) && !resources.getBoolean(R.bool.RecycledView)) {
            val binding = ActivityFilmListBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val list = binding.list
            registerForContextMenu(list)


            val films = mutableListOf<Film>()
            for (film: Film in FilmDataSource.films){
                films.add(film)
            }
             adapterFA = FilmsAdapter(
                this,
                R.layout.item_film, FilmDataSource.films
            )

            list.adapter = adapterFA

            list.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
            //list.setSelector(android.R.color.darker_gray);
            list.setMultiChoiceModeListener(
                object : MultiChoiceModeListener{
                    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                        val inflater = mode.menuInflater
                        inflater.inflate(R.menu.menu_contextual_multiple,menu)
                        val numElement = list.adapter.count
                        for (position :Int in 0 ..< numElement) {
                            list.getChildAt(position)
                                .findViewById<CheckBox>(R.id.item_check).visibility = View.VISIBLE
                        }
                        return true
                    }

                    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                        return false
                    }

                    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                        return when(item.itemId) {
                            R.id.deleteFilms ->{

                                val listOfIndexToRemove = ArrayList<Int>()

                                for (position: Int in 0..<adapterFA?.count!! )
                                {
                                    var isChecked = false
                                    isChecked = list.getChildAt(position).findViewById<CheckBox>(R.id.item_check).isChecked

                                    if (isChecked)
                                        listOfIndexToRemove.add(position)
                                }

                                listOfIndexToRemove.sortDescending()

                                for (position : Int in listOfIndexToRemove)
                                {
                                    val lastPositon = adapterFA?.count!! -1
                                    val checkBox = list.getChildAt(position).findViewById<CheckBox>(R.id.item_check)
                                    val lastCheckBox = list.getChildAt(lastPositon).findViewById<CheckBox>(R.id.item_check)

                                    if(checkBox.isChecked){
                                        checkBox.isChecked = false
                                        lastCheckBox.visibility= View.INVISIBLE
                                        FilmDataSource.films.removeAt(position)
                                    }
                                }
                                adapterFA?.notifyDataSetChanged()
                                listOfIndexToRemove.clear()
                                return true
                            }
                            else -> false
                        }
                    }

                    override fun onDestroyActionMode(mode: ActionMode?) {
                      val itemsRemain = adapterFA?.count

                        for (position : Int in 0..<itemsRemain!!)
                        {
                            val checkBox = list.getChildAt(position).findViewById<CheckBox>(R.id.item_check)
                            checkBox.isChecked = false
                            checkBox.visibility = View.INVISIBLE
                        }
                    }

                    override fun onItemCheckedStateChanged(
                        mode: ActionMode?,
                        position: Int,
                        id: Long,
                        checked: Boolean
                    ) {

                        if (checked){
                            val checkBox = list.getChildAt(position).findViewById<CheckBox>(R.id.item_check)

                            checkBox.isChecked = true
                            checkBox.visibility = View.VISIBLE
                        }
                    }
                }
            )

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
            this.adapterRV = adapter

            adapter.setOnItemCLickListener { position ->
                val intentFilm = Intent(this@FilmListActivity, FilmDataActivity::class.java)
                intentFilm.putExtra(FilmDataActivity.EXTRA_FILM_ID, position)
                startActivity(intentFilm)
            }

            adapter.setOnItemLongCLickListener { position ->
                (adapterRV as FilmRecycledViewListAdapter)?.actionMode = this@FilmListActivity.startActionMode(actionModeCallback)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_principal,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId){
            R.id.addFilm ->{
                val film = Film()
                FilmDataSource.films.add(film)

                adapterFA?.notifyDataSetChanged()
                adapterRV?.notifyItemInserted(FilmDataSource.films.size-1)

                return true
            }
            R.id.about ->{
                val intent = Intent(this@FilmListActivity, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return false
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual_multiple, menu)
        menu?.setHeaderTitle("Menú contextual")

    }
}