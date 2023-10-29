package es.eps.ua.drawable

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import es.eps.ua.drawable.databinding.ActivityHilosThreadBinding




class HilosThreadActivity : AppCompatActivity () {
    private lateinit var viewBinding : ActivityHilosThreadBinding

    private lateinit var tvCrono : TextView
    private lateinit var buttonInit : Button
    private lateinit var buttonBack : Button

    inner class CronoThread() : Thread(){

        override fun run (){
            var t = 10
            buttonInit.post{
              buttonInit.isEnabled = false
            }

            do {
                tvCrono.post {
                    tvCrono.text = "Contador: $t"
                }

                Thread.sleep(1000)
                t--
            } while (t > 0)
            tvCrono.post {
                tvCrono.text = "Contador terminado"
            }
            buttonInit.post{
                buttonInit.isEnabled = true
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHilosThreadBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        tvCrono = viewBinding.tvCronoTh
        buttonInit = viewBinding.buttonInitConometro
        buttonBack = viewBinding.buttonBackThread
        
        buttonInit.text = "Iniciar Conometro (Threads)"

        val cronoThread = CronoThread()
        
        buttonInit.setOnClickListener{
           cronoThread.start()
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }




}