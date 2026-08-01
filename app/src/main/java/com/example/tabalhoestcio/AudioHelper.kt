package com.example.tabalhoestcio

import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioDeviceInfo
import android.media.AudioManager

class AudioHelper(private val context: Context) {

    private val audioManager =
        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    fun hasSpeaker(): Boolean {

        if (!context.packageManager.hasSystemFeature(
                PackageManager.FEATURE_AUDIO_OUTPUT
            )
        ) {
            return false
        }

        return audioManager
            .getDevices(AudioManager.GET_DEVICES_OUTPUTS)
            .any {

                it.type == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER

            }
    }

    fun hasBluetoothHeadset(): Boolean {

        return audioManager
            .getDevices(AudioManager.GET_DEVICES_OUTPUTS)
            .any {

                it.type == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP
            }

    }

}