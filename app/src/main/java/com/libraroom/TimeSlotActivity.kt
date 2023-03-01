package com.libraroom

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*


class TimeSlotActivity : AppCompatActivity() {

    private lateinit var tvTimer: TextView
    var t1Hour = 0
    var t1Minute = 0
    var getTime = ""
    val db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_slot)

        val confirmBtn = findViewById<Button>(R.id.pickTimeBtn)
        confirmBtn.setOnClickListener{
            val day = intent.getStringExtra("day")
            val month = intent.getStringExtra("month")
            val year = intent.getStringExtra("year")
            val userReserve = hashMapOf(
                "User" to auth.currentUser?.uid,
                "date" to "$day/$month/$year",
                "time" to getTime)
            db.collection("reservation").add(userReserve).addOnSuccessListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


        }

        tvTimer = findViewById(R.id.timeTv)

        tvTimer.setOnClickListener{
            val timePickerDialog = TimePickerDialog(this, { view, hourOfDay, minute ->
                t1Hour = hourOfDay
                t1Minute = minute
                val calendar = Calendar.getInstance()
                calendar.set(0,0,0,t1Hour,t1Minute)
                Log.d("s",calendar.time.toString())
                val formatter = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
                val time = formatter.format(calendar.time)
                getTime = time.toString()
                tvTimer.setText(time.toString())
            },12,0,false)
            timePickerDialog.updateTime(t1Hour, t1Minute)
            timePickerDialog.show()
        }

    }
}
