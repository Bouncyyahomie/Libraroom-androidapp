package com.libraroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.CalendarView

class ReservationActivity : AppCompatActivity() {

    private lateinit var calendar: CalendarView
    private lateinit var btnTS: Button
    private var getDay = ""
    private var getMonth = ""
    private var getYear = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        calendar = findViewById(R.id.calendarView)
        btnTS = findViewById<Button>(R.id.btnTimeSlot)

        setupCalendar()
        btnTS.setOnClickListener{
            val intent = Intent(this, TimeSlotActivity::class.java)
            intent.putExtra("day",getDay)
            intent.putExtra("month",getMonth)
            intent.putExtra("year",getYear)
            startActivity(intent)
        }


    }
    private fun setupCalendar() {
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@ReservationActivity, msg, Toast.LENGTH_SHORT).show()
            getDay = dayOfMonth.toString()
            getMonth = (month+1).toString()
            getYear = year.toString()
        }
    }


}