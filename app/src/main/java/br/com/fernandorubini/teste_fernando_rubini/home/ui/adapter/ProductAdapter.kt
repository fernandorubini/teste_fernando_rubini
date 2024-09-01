package br.com.fernandorubini.teste_fernando_rubini.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.fernandorubini.teste_fernando_rubini.R
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.ProductModel
import br.com.fernandorubini.teste_fernando_rubini.home.ui.adapter.viewholder.ProductItemViewHolder

class ProductAdapter : RecyclerView.Adapter<ProductItemViewHolder>() {

    var productModels = emptyList<ProductModel>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ProductListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_products, parent, false)
        return ProductItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(productModels[position])
    }

    override fun getItemCount(): Int =
        productModels.size
}