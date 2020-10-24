package com.nicej.Irancell100SMS

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
    var MAX_SMS_MESSAGE_LENGTH = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            if (Build.VERSION.SDK_INT < 23) {
                //your code here
                sendSms(edit1.text.toString(), edit2.text.toString(), edit3.text.toString())
            } else {
                requestContactPermission();
            }
        }
    }

fun requestContactPermission() {

        var hasContactPermission =ActivityCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)

        if(hasContactPermission != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,   arrayOf(android.Manifest.permission.SEND_SMS), 1)
        } else {
            sendSms(edit1.text.toString(), edit2.text.toString(), edit3.text.toString())
        }
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.SEND_SMS) === PackageManager.PERMISSION_GRANTED)) {
                        sendSms(edit1.text.toString(), edit2.text.toString(), edit3.text.toString())
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
    private fun sendSms(phonenumber: String, nuumber: String, message: String) {
        val manager = SmsManager.getDefault()
        for (a in 0..nuumber.toInt())
            manager.sendTextMessage(phonenumber, null, message, null, null)

    }

}


