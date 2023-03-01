package com.libraroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SeatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)

        getReservationsFromFirebase { snapshot ->
            val reservationAdapter = ReservationAdapter(snapshot)
            val recyclerView = findViewById<RecyclerView>(R.id.reservationRecyclerView)

            recyclerView.apply {
                adapter = reservationAdapter
                layoutManager = LinearLayoutManager(this@SeatActivity)
            }
        }
    }

    private fun getReservationsFromFirebase(callback: (QuerySnapshot) -> Unit) {
        val db = Firebase.firestore
        val auth = FirebaseAuth.getInstance()
        db.collection("reservation")
            .whereEqualTo("User",auth.currentUser?.uid )
            .get()
            .addOnSuccessListener { result ->
                callback(result)
            }
    }

}