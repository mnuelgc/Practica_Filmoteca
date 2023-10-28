package es.eps.ua.drawable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsSeekBar
import android.widget.Button
import android.widget.SeekBar
import es.eps.ua.drawable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding

    private lateinit var buttonDrawables : Button
    private lateinit var buttonComponent : Button
    private lateinit var buttonNotificaciones : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        buttonDrawables = viewBinding.buttonDrawables
        buttonComponent = viewBinding.buttonComponents
        buttonNotificaciones = viewBinding.buttonNotificaciones

        buttonDrawables.setOnClickListener {
            val intent = Intent(this@MainActivity, DrawableExercicesActivity::class.java)
            startActivity(intent)
        }

        buttonComponent.setOnClickListener {
            val intent = Intent(this@MainActivity, PersonalizacionComponentesActivity::class.java)
            startActivity(intent)
        }

        buttonNotificaciones.setOnClickListener {
            val intent = Intent(this@MainActivity, NotificacionesActivity::class.java)
            startActivity(intent)
        }


    }
}