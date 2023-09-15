package com.example.mealprovider.model.recyclerview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealprovider.R
import com.example.mealprovider.databinding.ItemAndMealBinding
import kotlinx.android.synthetic.main.activity_list_meal.*

class MealInDaPlaceAdapter(
    private val items: Array<MealInDaPlace>,
    private val activity: Activity
) :
    RecyclerView.Adapter<MealInDaPlaceAdapter.ViewHolder>() {
    private var previousExpandedPosition: Int = -1

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAndMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val position = holder.adapterPosition

        val isExpanded = items[position].isExpanded
        holder.itemView.isActivated = isExpanded
        if (isExpanded) {
            previousExpandedPosition = position
        }

        holder.bindMealHere(items[position], activity)

        holder.itemView.setOnClickListener {
            if (previousExpandedPosition != -1) {
                items[previousExpandedPosition].isExpanded = false
            }
            items[position].isExpanded  = !isExpanded
            activity.andMealRecyclerView.smoothScrollToPosition(position)
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(position)
        }
    }

    class ViewHolder(private val binding: ItemAndMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindMealHere(mealInDaPlace: MealInDaPlace, activity: Activity) {
            with(mealInDaPlace) {
                binding.mealName.text = Name
            }
        }
    }


}