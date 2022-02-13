package com.example.tsapp.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsapp.R

class OnboardingItemsAdapter(private val onboardingItems: List<OnboardingItem>): RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemsViewHolder {
        return OnboardingItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemsViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

    inner class OnboardingItemsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val onboardingImage = view.findViewById<ImageView>(R.id.onboarding_image)
        private val textTitle = view.findViewById<TextView>(R.id.text_title)
        private val textDescription = view.findViewById<TextView>(R.id.text_description)

        fun bind(onboardingItem: OnboardingItem){
            onboardingImage.setImageResource(onboardingItem.onboardingImage)
            textTitle.text = onboardingItem.title
            textDescription.text = onboardingItem.description
        }
    }


}