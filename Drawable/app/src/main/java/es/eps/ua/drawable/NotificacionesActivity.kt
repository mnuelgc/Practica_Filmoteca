package es.eps.ua.drawable

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import es.eps.ua.drawable.databinding.ActivityNotificacionesBinding


class NotificacionesActivity : AppCompatActivity() {

    private lateinit var viewBinding : ActivityNotificacionesBinding
    private lateinit var buttonToast : Button
    private lateinit var editText : EditText

    private lateinit var buttonSnackbar : Button
    private lateinit var editTextSnackbar : EditText
    private lateinit var taskList : TextView
    private lateinit var button_ej3 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityNotificacionesBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        buttonToast = viewBinding.buttonToast
        editText = viewBinding.textEdit

        buttonSnackbar = viewBinding.buttonSnackBar
        editTextSnackbar = viewBinding.snackBarText
        taskList = viewBinding.taskList
        button_ej3 = viewBinding.buttonEj3

        buttonToast.setOnClickListener{
            LaunchToast()
        }

        buttonSnackbar.setOnClickListener{
            AddTask(it)
        }
        button_ej3.setOnClickListener {
            val intent = Intent(this@NotificacionesActivity, Ejercicio3Activity::class.java)
            startActivity(intent)
        }

    }




    private fun LaunchToast()
    {
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


    private fun AddTask(view: View) {

        if (editTextSnackbar.text.toString() !="")
        {
            val auxText = taskList.text.toString()
            taskList.append("${editTextSnackbar.text}\n")
            editTextSnackbar.setText("")
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            Snackbar.make(view, "Tarea añadida", Snackbar.LENGTH_LONG)
                .setAction("Deshacer") {
                    taskList.text = auxText
                }
                .show()
        }
        else{
            Snackbar.make(view, "Escribe un texto", Snackbar.LENGTH_SHORT).show()
        }
    }
}