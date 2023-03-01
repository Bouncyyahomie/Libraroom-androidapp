package com.libraroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.btnReservation)
        buttonClick.setOnClickListener{
            val intent = Intent(this@MainActivity, ReservationActivity::class.java)
            startActivity(intent)
        }

        val buttonClick2 = findViewById<Button>(R.id.btnSeat)
        buttonClick2.setOnClickListener{
            val intent = Intent(this@MainActivity, SeatActivity::class.java)
            startActivity(intent)
        }
    }
}