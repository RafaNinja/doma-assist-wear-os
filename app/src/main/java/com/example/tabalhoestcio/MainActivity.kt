package com.example.tabalhoestcio

import android.content.Intent
import android.provider.Settings
import android.os.Bundle
import android.media.MediaPlayer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        Toast.makeText(
            this,
            "Versão de teste",
            Toast.LENGTH_LONG
        ).show()

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val txtSpeaker =
            findViewById<TextView>(R.id.txtSpeaker)


        val btnVerificar =
            findViewById<Button>(R.id.btnVerificar)

        val txtBluetooth =
            findViewById<TextView>(R.id.txtBluetooth)

        val btnAudio =
            findViewById<Button>(R.id.btnAudio)

        val btnBluetooth =
            findViewById<Button>(R.id.btnBluetooth)

        val audioHelper = AudioHelper(this)

        // Botão Verificar
        btnVerificar.setOnClickListener {

            if (audioHelper.hasSpeaker()) {

                txtSpeaker.text =
                    "🔊 Speaker: ✔ Disponível"

            } else {

                txtSpeaker.text =
                    "🔊 Speaker: ❌ Offline"
            }

            if (audioHelper.hasBluetoothHeadset()) {

                txtBluetooth.text =
                    "🎧 Bluetooth: ✔ Conectado"

            } else {

                txtBluetooth.text =
                    "🎧 Bluetooth: ❌ Offline"
            }
        }

        btnBluetooth.setOnClickListener {

            val intent =
                Intent(Settings.ACTION_BLUETOOTH_SETTINGS)

            startActivity(intent)
        }

        // Botão Reproduzir Áudio
        btnAudio.setOnClickListener {

            val mediaPlayer = MediaPlayer.create(
                this,
                R.raw.audio_teste
            )

            mediaPlayer?.start()

            mediaPlayer?.setOnCompletionListener {
                mediaPlayer.release()
            }

        }
        }

    }
