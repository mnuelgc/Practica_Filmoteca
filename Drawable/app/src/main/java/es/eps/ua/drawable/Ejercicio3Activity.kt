package es.eps.ua.drawable

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import es.eps.ua.drawable.databinding.ActivityEjercicio3Binding
import es.eps.ua.drawable.databinding.ActivityNotificacionesBinding

class Ejercicio3Activity : AppCompatActivity() {

    private lateinit var viewBinding : ActivityEjercicio3Binding
    private lateinit var buttonColor : Button
    private lateinit var buttonSize : Button
    private lateinit var text : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEjercicio3Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        buttonColor = viewBinding.colorButton
        buttonSize = viewBinding.tamanyoButton
        text = viewBinding.principalText

        text.text ="Hay dos tipos de personas en el mundo, hijo -dijo su padre severamente-. Los que salvan vidas. Y los que las quitan. - ¿Y los que protegen y defienden? ¿Los que salvan vidas quitando vidas? Su padre bufó. -Eso es como intentar detener una tormenta soplando más fuerte. Ridículo. No se puede proteger matando. \n\n Vida antes que muerte. ¿Qué significaba el dicho? ¿Que los hombres deberían buscar la vida antes que buscar la muerte? Eso era obvio. ¿O significaba otra cosa? ¿Que la vida venía antes que la muerte? Una vez más, obvio. Y sin embargo las palabras sencillas le hablaban. La muerte viene, susurraban. La muerte les viene a todos. Pero la vida viene primero. Saboréala. La muerte es el destino. Pero el viaje, eso es la vida. Eso es lo que importa. \n\n ¿Importa el destino? ¿O es el camino que emprendemos? Declaro que ningún logro tiene tan gran sustancia como el camino empleado para conseguirlo. No somos criaturas de destinos. Es el viaje el que nos da la forma. Nuestros pies encallecidos, nuestras espaldas fortalecidas por cargar el peso de nuestros viajes, nuestros ojos abiertos con el fresco deleite de las experiencias vividas."


        buttonColor.setOnClickListener{
            AlertDialog.Builder(this@Ejercicio3Activity!!)
                .setTitle("Selección de color")
                .setMessage("Elige la combinación de colores")
                .setPositiveButton("Blanco y Negro") { dialog, id -> changeColor(id) }
                .setNegativeButton("Negro y Blanco") { dialog, id ->   changeColor(id) }
                .setNeutralButton("Negro y Verde") { dialog, id ->   changeColor(id)  }
                .show()
        }

        buttonSize.setOnClickListener{
            AlertDialog.Builder(this@Ejercicio3Activity!!)
                .setTitle("Selección de tamaño")
                .setMessage("Elige el tamaño de la letra")
                .setPositiveButton("Fuente Pequeña") { dialog, id -> changeSize(id) }
                .setNegativeButton("Fuente Mediana") { dialog, id ->   changeSize(id) }
                .setNeutralButton("Fuente Grande") { dialog, id ->   changeSize(id)  }
                .show()
        }



    }
    fun changeColor(id: Int)
    {

        when(id){
            -1 ->{
                text.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                text.setTextColor(ContextCompat.getColor(this, R.color.black))
            }
            -2 -> {
                text.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
                text.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
            -3 -> {
                text.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
                text.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        }

    }

    fun changeSize(id: Int)
    {
        when(id){
            -1 ->{
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8F)
            }
            -2 -> {
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
            }
            -3 -> {
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            }
        }
    }
}