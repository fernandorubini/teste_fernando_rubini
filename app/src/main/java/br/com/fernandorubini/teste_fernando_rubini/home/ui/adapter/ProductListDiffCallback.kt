package br.com.fernandorubini.teste_fernando_rubini.home.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.ProductModel


class ProductListDiffCallback(
    private val oldList: List<ProductModel>,
    private val newList: List<ProductModel>
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