package es.eps.ua.drawable

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import es.eps.ua.drawable.databinding.ActivityNotificacionesBinding

class NotificacionesActivity : AppCompatActivity() {

    private lateinit var viewBinding : ActivityNotificacionesBinding
    private lateinit var buttonToast : Button
    private lateinit var editText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityNotificacionesBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        buttonToast = viewBinding.buttonToast
        editText = viewBinding.textEdit



        buttonToast.setOnClickListener{



            var textToShow = ""
            if (editText.text.toString() != "")
            {
                textToShow = editText.text.toString()
                editText.setText("")
            }else{
                textToShow = "Escribe un texto"
            }

            val layout = layoutInflater.inflate(R.layout.toast_layout, null)
            layout!!.findViewById<TextView>(R.id.txtMensaje).text = textToShow

            val toast = Toast(this@NotificacionesActivity)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = layout

            if (Build.VERSION.SDK_INT < 30) {
                toast.setGravity(Gravity.CENTER, 0, 0)
            }else

            toast.show()
        }


    }
}