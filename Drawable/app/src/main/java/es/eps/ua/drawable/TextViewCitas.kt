package es.eps.ua.drawable

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import kotlin.random.Random


val citas : Array<String> = arrayOf(
    "El que saca la pistola para enseñarla es un parguela, la pistola si se saca es para disparar",
    "¿Quieres 50 euros o no?",
    "Yo fumo pah hacerme el chulo, pero por ti lo dejo Patricia",
    "Cuando tu vas yo he ido, he vuelto y he ido, he estado en tres centros de menores ¿Me entiendes o no?",
    "El rey es mi padre",
    "Sin animo de lucro, ¿Tu que prefieres una tortilla de patatas o que te devuelvan siete cajas de valeriana?")

class TextViewCitas : AppCompatTextView {
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int)
            : super(context, attrs, defStyle) {
                this.text = "\" ${SelectQuote()}\" "
            }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.text ="\" ${SelectQuote()}\" "
    }
    constructor(context: Context) : super(context) {
        this.text = "\" ${SelectQuote()}\" "
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.text = "\" ${SelectQuote()}\" "
        return super.onTouchEvent(event)
    }

    private fun SelectQuote() :String{

        val randomIndex =  Random.nextInt(0, citas.size)

        return citas[randomIndex]
    }
}