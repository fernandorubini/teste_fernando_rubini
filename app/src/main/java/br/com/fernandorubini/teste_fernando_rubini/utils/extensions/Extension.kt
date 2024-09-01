package br.com.fernandorubini.teste_fernando_rubini.utils.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}