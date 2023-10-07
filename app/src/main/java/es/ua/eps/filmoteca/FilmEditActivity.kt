package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.AUTHORITY
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import es.ua.eps.filmoteca.databinding.ActivityFilmEditBinding
import java.io.File


class FilmEditActivity : AppCompatActivity() {

    var imgfield: ImageView? = null

    companion object Extra {
        const val EXTRA_FILM_IMAGE = "EXTRA_FILM_IMAGE"
        val IMAGE_REQUEST_CODE = 100
        val TAKE_IMAGE_REQUEST_CODE = 200

    }

    private val startAskImageForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result : ActivityResult -> onActivityResult(IMAGE_REQUEST_CODE, result.resultCode, result.data)}

    private val startTakeImageForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result : ActivityResult -> onActivityResult(TAKE_IMAGE_REQUEST_CODE, result.resultCode, result.data)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFilmEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val buttonTakePicture = binding.takePhoto
        val buttonSelectFromGallery = binding.SelectFromGallery
        val buttonSave = binding.saveButton
        val buttonCancel = binding.cancelButton
        val extraIntent = intent


        imgfield = binding.imgfilm
        val img = extraIntent.getIntExtra(FilmEditActivity.EXTRA_FILM_IMAGE, R.drawable.ic_launcher_foreground)
        imgfield?.setImageResource(img)


        buttonSelectFromGallery.setOnClickListener{
            pickImageGallery()
        }

        buttonTakePicture.setOnClickListener {
            takeAPhoto()
        }

        buttonSave.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun takeAPhoto() {
        Toast.makeText(this@FilmEditActivity, "PHOTO",Toast.LENGTH_LONG).show()
     /*   val f = File(Environment.getExternalStorageDirectory(), "temp.jpg")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        intent.putExtra(
            MediaStore.EXTRA_OUTPUT,
            FileProvider.getUriForFile(this@FilmEditActivity, AUTHORITY, f)
        )
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)


        if (Build.VERSION.SDK_INT >= 30)
        {
            startTakeImageForResult.launch(intent)
        }
        else {
            @Suppress("DEPRECATION")
            startActivityForResult(intent, TAKE_IMAGE_REQUEST_CODE)
        }
*/
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"


        if (Build.VERSION.SDK_INT >= 30)
        {
            startAskImageForResult.launch(intent)
        }
        else {
            @Suppress("DEPRECATION")
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        @Suppress ("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode)
        {
            IMAGE_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK)
            {
                imgfield?.setImageURI(data?.data)
            }
            TAKE_IMAGE_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK){
                imgfield?.setImageURI(data?.data)
            }
        }
    }
}