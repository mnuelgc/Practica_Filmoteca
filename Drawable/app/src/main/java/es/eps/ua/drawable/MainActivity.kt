package es.eps.ua.drawable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import es.eps.ua.drawable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding

    private lateinit var buttonDrawables : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        buttonDrawables = viewBinding.buttonDrawables


        buttonDrawables.setOnClickListener {
            val intent = Intent(this@MainActivity, DrawableExercicesActivity::class.java)
            startActivity(intent)
        }
    }
}