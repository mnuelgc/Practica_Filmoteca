package es.ua.eps.filmoteca

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.AUTHORITY
import android.service.autofill.OnClickAction
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.PackageManagerCompat
import es.ua.eps.filmoteca.databinding.ActivityFilmEditBinding
import java.io.File


class FilmEditActivity : AppCompatActivity() {

    var imgfield: ImageView? = null

    val PERMISSION_USE_CAMERA = Manifest.permission.CAMERA
    val PERMISION_CAMERA_REQUEST_CODE = 1001

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
        RequestCameraPermision()
    }

    private fun takeAPhotoIntent()
    {
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (callCameraIntent.resolveActivity(packageManager)!= null){
            if (Build.VERSION.SDK_INT >= 30)
            {
                startTakeImageForResult.launch(callCameraIntent)
            }
            else {
                @Suppress("DEPRECATION")
                startActivityForResult(callCameraIntent, TAKE_IMAGE_REQUEST_CODE)
            }
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"

        if (intent.resolveActivity(packageManager)!= null) {

            if (Build.VERSION.SDK_INT >= 30) {
                startAskImageForResult.launch(intent)
            } else {
                @Suppress("DEPRECATION")
                startActivityForResult(intent, IMAGE_REQUEST_CODE)
            }
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
            TAKE_IMAGE_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK && data != null){
                imgfield?.setImageBitmap(data.extras?.get("data") as Bitmap)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISION_CAMERA_REQUEST_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }else if (!ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_USE_CAMERA)){
                val builder = AlertDialog.Builder(this)
                builder.setMessage(resources.getString(R.string.FeatureUnavailable))
                    .setTitle(resources.getString(R.string.PermissionRequired))
                    .setCancelable(false)
                    .setNegativeButton(resources.getString(R.string.Cancel), fun(dialog: DialogInterface, wich : Int){dialog.dismiss()})
                    .setPositiveButton(resources.getString(R.string.Ok),fun(dialog: DialogInterface, wich : Int){dialog.dismiss()})
                builder.show()
            }
            else {
                RequestCameraPermision()
            }
        }
    }

    fun RequestCameraPermision()
    {
        if (ContextCompat.checkSelfPermission(this,
                PERMISSION_USE_CAMERA)
            == PackageManager.PERMISSION_GRANTED)
        {
            takeAPhotoIntent()
        }
        else if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_USE_CAMERA))
        {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(resources.getString(R.string.PermissionRequiredExplain))
                .setTitle(resources.getString(R.string.PermissionRequest))
                .setCancelable(false)
                .setPositiveButton(resources.getString(R.string.Ok), fun(dialog: DialogInterface, wich : Int){
                    ActivityCompat.requestPermissions(this, arrayOf(PERMISSION_USE_CAMERA), PERMISION_CAMERA_REQUEST_CODE)
                    dialog.dismiss()
                })
                .setNegativeButton(resources.getString(R.string.Cancel), fun(dialog: DialogInterface, wich : Int){dialog.dismiss()})
            builder.show()
        }else{
            ActivityCompat.requestPermissions(this,
                arrayOf(PERMISSION_USE_CAMERA), PERMISION_CAMERA_REQUEST_CODE )
        }
    }
}