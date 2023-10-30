package es.eps.ua.drawable

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.eps.ua.drawable.databinding.ActivityPersonalizacionComponentesBinding


class PersonalizacionComponentesActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityPersonalizacionComponentesBinding
    private lateinit var seekBar: SeekBar
    private lateinit var grafComp: GrafComp
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        viewBinding = ActivityPersonalizacionComponentesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        seekBar = viewBinding.seekBar
        grafComp = viewBinding.grafica

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                grafComp.setPercentage(seekBar.progress)
            }
        })

        val buttonVolver : Button = viewBinding.buttonBack
        buttonVolver.setOnClickListener{
            finish()
        }
    }
}