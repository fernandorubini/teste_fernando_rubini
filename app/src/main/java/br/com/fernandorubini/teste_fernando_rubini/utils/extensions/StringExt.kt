package br.com.fernandorubini.teste_fernando_rubini.utils.extensions

import android.util.Patterns
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants.ELEVEN
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants.NINE
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants.SPACE
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants.TWO
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants.ZERO

fun String.toDateApi(): String {
    if (this.isNullOrEmpty()) return Constants.EMPTY

    var date = this.split("/")
    return "${date[2]}-${date[1]}-${date[0]}"
}

fun String.validFullName(): Boolean {
    var space = this.split(" ")
    return space.isNotEmpty() && space.size >= 2
}

fun String?.isNotNullOrBlank(): Boolean {
    return this.isNullOrBlank().not()
}

fun String.validPhone(): Boolean {
    var amount = this.length
    return amount >= 14
}

fun String.removeSpaces(): String {
    return this.replace(SPACE, String())
}

fun String.unMask() = this.replace("[^0-9]".toRegex(), "")

fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidCPF(function: (String) -> Unit = {}): Boolean =
    if (this.isNotEmpty() && this.unMask().trim().length == ELEVEN) {
        function.invoke(this)
        true
    } else {
        false
    }
fun String.isValidPhone(): Boolean {
    val inputPhone = this.trim()

    if (inputPhone.isEmpty()) {
        return true
    }

    val number: String = inputPhone.unMask()
    if (number.length < ELEVEN) {
        return false
    }
    if (number.length == 11 && number[TWO] != '9') {
        return false
    }
    val list = codeAreas.toList()
    return list.contains(number.substring(ZERO, TWO))
}

fun String.isValidName(): Boolean = this.isNotEmpty()
        && this.trim().split(" ").size >= Constants.TWO
        && this.trim().length >= Constants.FOUR

private val codeAreas = arrayOf(
    "11",
    "12",
    "13",
    "14",
    "15",
    "16",
    "17",
    "18",
    "19",
    "21",
    "22",
    "24",
    "27",
    "28",
    "31",
    "32",
    "33",
    "34",
    "35",
    "37",
    "38",
    "41",
    "42",
    "43",
    "44",
    "45",
    "46",
    "47",
    "48",
    "49",
    "51",
    "53",
    "54",
    "55",
    "61",
    "62",
    "63",
    "64",
    "65",
    "66",
    "67",
    "68",
    "69",
    "71",
    "73",
    "74",
    "75",
    "77",
    "79",
    "81",
    "82",
    "83",
    "84",
    "85",
    "86",
    "87",
    "88",
    "89",
    "91",
    "92",
    "93",
    "94",
    "95",
    "96",
    "97",
    "98",
    "99"
)
