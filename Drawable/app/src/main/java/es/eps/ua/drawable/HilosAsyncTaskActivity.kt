package es.eps.ua.drawable

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import es.eps.ua.drawable.databinding.ActivityHilosAsyncTaskBinding
import es.eps.ua.drawable.databinding.ActivityHilosThreadBinding




class HilosAsyncTaskActivity : AppCompatActivity() {

    private lateinit var viewBinding : ActivityHilosAsyncTaskBinding

    private lateinit var tvCrono : TextView
    private lateinit var buttonInit : Button
    private lateinit var buttonBack : Button

    inner class CronoAsyncTask: AsyncTask<Void, Int, Int>(){
        @Deprecated("Deprecated in Java")
        override fun onPreExecute() {
            tvCrono.text = "Contador: 10"
        }
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): Int? {
            var t = 10
            do {
                publishProgress(t)
                Thread.sleep(1000)
                t--
            } while (t > 0)

            return 0
        }
        @Deprecated("Deprecated in Java")
        override fun onProgressUpdate(vararg values: Int?) {
            tvCrono.text =  "Contador: ${values[0]}"
        }
        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Int) {
            tvCrono.text = "Contador terminado"
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHilosAsyncTaskBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        tvCrono = viewBinding.tvCronoAs
        buttonInit = viewBinding.buttonInitConometro
        buttonBack = viewBinding.buttonBackAs


        buttonInit.text = "Iniciar Conometro (AsyncTask)"



        buttonInit.setOnClickListener{
            CronoAsyncTask().execute()
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }
}