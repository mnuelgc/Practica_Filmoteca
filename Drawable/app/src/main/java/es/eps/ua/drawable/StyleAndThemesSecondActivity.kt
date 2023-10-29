package es.eps.ua.drawable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import es.eps.ua.drawable.databinding.ActivityStyleAndThemesBinding
import es.eps.ua.drawable.databinding.ActivityStyleAndThemesSecondBinding

class StyleAndThemesSecondActivity : AppCompatActivity() {

    private lateinit var viewBinding : ActivityStyleAndThemesSecondBinding

    private lateinit var buttonBack : Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding = ActivityStyleAndThemesSecondBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        buttonBack = viewBinding.buttonStyleAndThemes

        buttonBack.setOnClickListener {
            finish()
        }
    }
}