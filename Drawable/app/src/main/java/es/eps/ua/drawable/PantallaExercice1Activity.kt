package es.eps.ua.drawable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import es.eps.ua.drawable.databinding.ActivityPantallaBinding
import es.eps.ua.drawable.databinding.ActivityPantallaExercice1Binding

class PantallaExercice1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val viewBinding = ActivityPantallaExercice1Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val buttonVolver : Button = viewBinding.buttonBack
        buttonVolver.setOnClickListener{
            finish()
        }

    }


}