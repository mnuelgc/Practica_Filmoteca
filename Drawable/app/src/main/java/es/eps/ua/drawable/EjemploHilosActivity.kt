package es.eps.ua.drawable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import es.eps.ua.drawable.databinding.ActivityEjemploHilosBinding
import es.eps.ua.drawable.databinding.ActivityMainBinding

class EjemploHilosActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityEjemploHilosBinding

    private lateinit var tvCrono : TextView
    private lateinit var buttonInit : Button
    private lateinit var buttonThread : Button
    private lateinit var buttonAsync : Button
    private lateinit var buttonCoroutine : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEjemploHilosBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        tvCrono = viewBinding.tvCrono
        buttonInit = viewBinding.buttonInitConometro

        buttonThread = viewBinding.buttonCronometroThread
        buttonAsync = viewBinding.buttonAsyncTask
        buttonCoroutine = viewBinding.buttonCorroutine

        buttonInit.text = "Iniciar Conometro (El del ejemplo del enunciado [No funciona])"
        buttonInit.setOnClickListener{

            val tvCrono = findViewById<TextView>(R.id.tvCrono)
            var t = 10
            do {
                tvCrono.text = "Contador: $t"
                Thread.sleep(1000)
                t--
            } while (t > 0)
            tvCrono.text = "Contador terminado"
        }


        buttonThread.setOnClickListener {
            val intent = Intent(this@EjemploHilosActivity, HilosThreadActivity::class.java)
            startActivity(intent)
        }
    }
}