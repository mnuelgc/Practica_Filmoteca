package es.eps.ua.drawable

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import es.eps.ua.drawable.databinding.ActivityHilosAsyncTaskBinding
import es.eps.ua.drawable.databinding.ActivityHilosCorrutinasBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HilosCorrutinasActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityHilosCorrutinasBinding

    private lateinit var tvCrono : TextView
    private lateinit var buttonInit : Button
    private lateinit var buttonBack : Button

    private fun cronometra()
    {
        var t = 10
        do {
            tvCrono.text = "Contador: $t"
            Thread.sleep(1000)
            t--
        } while (t > 0)
        tvCrono.text = "Contador terminado"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHilosCorrutinasBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        tvCrono = viewBinding.tvCronoCo
        buttonInit = viewBinding.buttonInitConometro
        buttonBack = viewBinding.buttonBackCo


        buttonInit.text = "Iniciar Conometro (Corrutina)"



        buttonInit.setOnClickListener{

            GlobalScope.launch {
                cronometra()
            }
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }
}