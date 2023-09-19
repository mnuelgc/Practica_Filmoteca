package es.ua.eps.filmoteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import es.ua.eps.filmoteca.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonWeb = binding.button
        buttonWeb.setOnClickListener {
            Toast.makeText(this, "Funcionalidad sin implementar", Toast.LENGTH_LONG).show()
        }

        val buttonSup = binding.button2
        buttonSup.setOnClickListener {
            Toast.makeText(this, "Funcionalidad sin implementar", Toast.LENGTH_LONG).show()
        }

        val buttonBack = binding.button3

        buttonBack.setOnClickListener {
            Toast.makeText(this, "Funcionalidad sin implementar", Toast.LENGTH_LONG).show()
        }
    }
}