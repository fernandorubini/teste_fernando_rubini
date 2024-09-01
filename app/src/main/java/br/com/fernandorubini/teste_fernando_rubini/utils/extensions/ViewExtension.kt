package br.com.fernandorubini.teste_fernando_rubini.utils.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import br.com.fernandorubini.teste_fernando_rubini.R

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.visibility(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun renderHtml(html: String): Spanned {
    return if (verifySdkVersion(Build.VERSION_CODES.N)) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(html)
    }
}

fun verifySdkVersion(version: Int): Boolean {
    return Build.VERSION.SDK_INT >= version
}

fun AppCompatImageView.setCheckUncheckedImage(isCheck: Boolean?) {
    isCheck?.let {
        this.setImageDrawable(
            if (it) resources.getDrawable(R.drawable.ic_checked, null)
            else resources.getDrawable(R.drawable.ic_blocked, null)
        )
    }
}