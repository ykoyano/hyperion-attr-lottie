package com.github.ykoyano.hyperion.plugin.attr.lottie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView


class CustomAdapter(private val dataSet: List<String>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.title) as TextView
        val likeAnimationView = itemView.findViewById(R.id.like_animation_view) as LottieAnimationView
        var isActive = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cards_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, index: Int) {
        holder.title.text = dataSet[index]
        holder.likeAnimationView.setOnClickListener {
            val animationView = (it as LottieAnimationView)
            when (holder.isActive) {
                false -> {
                    holder.isActive = true
                    animationView.playAnimation()
                }
                true -> {
                    holder.isActive = false
                    animationView.frame = 0
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
