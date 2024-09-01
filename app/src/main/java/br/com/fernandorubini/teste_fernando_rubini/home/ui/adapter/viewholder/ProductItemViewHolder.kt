package br.com.fernandorubini.teste_fernando_rubini.home.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import br.com.fernandorubini.teste_fernando_rubini.R
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.ProductModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ProductItemViewHolder constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.findViewById<ImageView>(R.id.imgMainItem)
    private val progress = itemView.findViewById<ProgressBar>(R.id.progressImage)

    fun bind(productModel: ProductModel) {

        imageView.contentDescription = productModel.name
        progress.visibility = View.VISIBLE

        Picasso.get()
            .load(productModel.imageURL)
            .error(R.drawable.ic_alert_circle)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progress.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progress.visibility = View.GONE
                }
            })
    }
}