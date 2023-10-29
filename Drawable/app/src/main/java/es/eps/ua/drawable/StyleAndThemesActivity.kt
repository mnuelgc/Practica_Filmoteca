package es.eps.ua.drawable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import es.eps.ua.drawable.databinding.ActivityMainBinding
import es.eps.ua.drawable.databinding.ActivityStyleAndThemesBinding

class StyleAndThemesActivity : AppCompatActivity() {

    private lateinit var viewBinding : ActivityStyleAndThemesBinding

    private lateinit var buttonContinue : Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding = ActivityStyleAndThemesBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        buttonContinue = viewBinding.buttonStyleAndThemes

        buttonContinue.setOnClickListener {
            val intent = Intent(this@StyleAndThemesActivity, StyleAndThemesSecondActivity::class.java)
            startActivity(intent)
        }

    }
}