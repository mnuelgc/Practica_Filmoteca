package es.eps.ua.drawable

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import es.eps.ua.drawable.databinding.ActivityEjercicio3Binding
import es.eps.ua.drawable.databinding.ActivityNotificationExercice4Binding

class NotificationExercice4 : AppCompatActivity() {
    private lateinit var viewBinding : ActivityNotificationExercice4Binding
    private lateinit var buttonIniciar : Button
    private lateinit var buttonDetener : Button

    private var tasksRunning = 0

    public val CHANNEL_ID = "channel_id"



    val permission = android.Manifest.permission.POST_NOTIFICATIONS

    val REQUEST_PERMISSION_CODE = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNotificationExercice4Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        buttonIniciar = viewBinding.iniciarButton
        buttonDetener = viewBinding.detenerButton



        if (ContextCompat.checkSelfPermission(this, permission)

            != PackageManager.PERMISSION_GRANTED) {

            // ¿Mostrar explicación?

            if (ActivityCompat.shouldShowRequestPermissionRationale(

                    this, permission)) {

                // Mostrar explicación adicional

            } else {

                // Solicitamos el permiso...

                ActivityCompat.requestPermissions(this,

                    arrayOf(permission), REQUEST_PERMISSION_CODE)

            }

        }



        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.spider_info_small)
            .setContentTitle("Iniciando Tareas")
            .setContentText("Tareas iniciadas ${tasksRunning.toString()}")
            .setPriority(NotificationCompat.PRIORITY_MAX)
        val notificationManager = NotificationManagerCompat.from(this)
        val NOTIF_ID = 1

        buttonIniciar.setOnClickListener{
            tasksRunning++
            createNotificationChannel()

            builder.setContentText("Tareas iniciadas ${tasksRunning.toString()}")

            builder.setContentIntent(pendingIntent)


            notificationManager.notify(NOTIF_ID, builder.build())
        }

        buttonDetener.setOnClickListener{
            if (tasksRunning >0) {
                tasksRunning--
                if (tasksRunning >0) {
                    builder.setContentText("Tareas iniciadas ${tasksRunning.toString()}")
                    notificationManager.notify(NOTIF_ID, builder.build())
                }else{
                    notificationManager.cancelAll()
                }
            }
        }

    }

    private fun createNotificationChannel() {
        // Crear el canal solo para API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel_name"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = "channel_Description"

            // Registramos el canal en el sistema
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



}