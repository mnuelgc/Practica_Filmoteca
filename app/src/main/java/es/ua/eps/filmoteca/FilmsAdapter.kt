package es.ua.eps.filmoteca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class FilmsAdapter(context: Context?, resource: Int,
                   objects: List<Film>?
                ) : ArrayAdapter<Film>(context!!, resource, objects!!){
      override fun getView(position: Int, convertView: View?,
                           parent: ViewGroup) : View {
          var view : View = convertView?: LayoutInflater.from(this.context)
                                .inflate(R.layout.item_film, parent, false)
          val tvTitle = view.findViewById<TextView>(R.id.title) as TextView
          val tvDirector = view.findViewById<TextView>(R.id.director) as TextView
          val tvImage = view.findViewById<TextView>(R.id.itemImg) as ImageView

          getItem(position)?.let{
              tvTitle.text = it.title
              tvDirector.text = it.director

              it.convertImageDrawableToBitmap(context)
              tvImage.setImageBitmap(it.imageBitmap)
          }
          return view
      }
}
