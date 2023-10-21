package es.eps.ua.drawable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class EdicionBorrable : LinearLayout {
    var editText : EditText? = null
    var button : Button? = null
    constructor(ctx : Context?) : super(ctx) {
        initialize()
    }
    constructor(ctx : Context?, attrs: AttributeSet?) : super(ctx, attrs) {
        initialize()
    }


    private fun initialize() {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        li.inflate(R.layout.edicion_borrable, this, true)

        editText = findViewById<EditText>(R.id.editText)
        button = findViewById<Button>(R.id.button_ed_borrable)

        button!!.setOnClickListener { editText!!.setText("") }
    }

}