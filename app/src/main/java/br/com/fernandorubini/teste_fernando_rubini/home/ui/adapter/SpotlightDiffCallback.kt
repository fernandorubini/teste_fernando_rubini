package br.com.fernandorubini.teste_fernando_rubini.home.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.SpotlightModel


class SpotlightDiffCallback (
    private val oldList: List<SpotlightModel>,
    private val newList: List<SpotlightModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        true
}