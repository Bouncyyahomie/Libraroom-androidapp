package com.libraroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class ReservationAdapter(
    private val snapshot: QuerySnapshot
) : RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        return ReservationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.reserve_entry, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.bind(snapshot.documents[position])
    }

    override fun getItemCount(): Int = snapshot.size()

    inner class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.reserveTxt)
        val time = itemView.findViewById<TextView>(R.id.timeTxt)

        fun bind(document: DocumentSnapshot) {
            date.text = document.data?.get("date") as String
            time.text = document.data?.get("time") as String
        }
    }
}