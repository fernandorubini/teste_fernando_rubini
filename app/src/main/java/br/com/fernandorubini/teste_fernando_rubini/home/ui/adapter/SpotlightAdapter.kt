package br.com.fernandorubini.teste_fernando_rubini.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.fernandorubini.teste_fernando_rubini.R
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.SpotlightModel
import br.com.fernandorubini.teste_fernando_rubini.home.ui.adapter.viewholder.SpotlightItemViewHolder

class SpotlightAdapter : RecyclerView.Adapter<SpotlightItemViewHolder>() {

    var spotlightModels = emptyList<SpotlightModel>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                SpotlightDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_spotlight, parent, false)
        return SpotlightItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpotlightItemViewHolder, position: Int) {
        holder.bind(spotlightModels[position])
    }

    override fun getItemCount(): Int =
        spotlightModels.size
}