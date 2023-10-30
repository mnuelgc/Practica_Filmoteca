package es.eps.ua.drawable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import es.eps.ua.drawable.databinding.ActivityDrawableExercicesBinding
import es.eps.ua.drawable.databinding.ActivityMainBinding

class DrawableExercicesActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityDrawableExercicesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDrawableExercicesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        val buttonVolver : Button = viewBinding.buttonBack
        buttonVolver.setOnClickListener{
            finish()
        }
    }
}