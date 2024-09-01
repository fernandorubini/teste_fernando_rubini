package br.com.fernandorubini.teste_fernando_rubini.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object MaskUtils {
    const val maskCPF = "###.###.###-##"
    private const val maskCEP = "#####-###"
    private const val maskBirthDate = "##/##/####"
    private const val maskPhoneNumber = "(##)#####-####"
    fun mask(editText: EditText): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var old = ""
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = unmask(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in maskCPF.toCharArray()) {
                    if (m != '#' && str.length > old.length || m != '#' && str.length < old.length && str.length != i) {
                        mascara += m
                        continue
                    }
                    mascara += try {
                        str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(mascara)
                editText.setSelection(mascara.length)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }

    fun mask(editText: EditText, mask: String): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var old = ""
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = unmask(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > old.length || m != '#' && str.length < old.length && str.length != i) {
                        mascara += m
                        continue
                    }
                    mascara += try {
                        str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(mascara)
                editText.setSelection(mascara.length)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }

    fun getMaskCPFCNPJ(cpfCnpj: String): String {
        var mascara = ""
        var i = 0
        for (m in maskCPF.toCharArray()) {
            if (m != '#' && cpfCnpj.length != i) {
                mascara += m
                continue
            }
            mascara += try {
                cpfCnpj[i]
            } catch (e: Exception) {
                break
            }
            i++
        }
        return mascara
    }

    /**
     * @param mask8  Mask of 8 digits phone number
     * @param mask9  Mask of 9 digits phone number
     * @param ediTxt EditText to be applied the mask
     * @return
     */
    fun phoneMask(mask8: String?, mask9: String?, ediTxt: EditText): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var old = ""
            var mask: String? = null
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var str = unmask(s.toString())
                mask = if (str.length < 11) {
                    mask8
                } else {
                    mask9
                }
                if (str.length > 9) {
                    str = removeLeftZero(str)
                }
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask!!.toCharArray()) {
                    if (m != '#' && str.length != old.length) {
                        mascara += m
                        continue
                    }
                    mascara += try {
                        str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        }
    }

    private fun removeLeftZero(s: String): String {
        return if (s.substring(0, 1) == "0") {
            s.substring(1, s.length)
        } else {
            s
        }
    }

    fun unmask(s: String?): String {
        return s?.replace("[^0-9]".toRegex(), "") ?: ""
    }

    fun unmaskMsisdn(phone: String?): String {
        val unmaskedPhone = unmask(phone)
        return "55$unmaskedPhone"
    }

    fun formatPhone(phone: String): String {
        return phone.replace(
            "([0-9]{2})([0-9]{5}|[0-9]{4})([0-9]{4})".toRegex(), "($1) $2-$3"
        )
    }

    fun formatBrazilPhone(phone: String): String {
        return phone.substring(2).replace(
            "([0-9]{2})([0-9]{5}|[0-9]{4})([0-9]{4})".toRegex(), "($1) $2-$3"
        )
    }

    fun insert(editText: EditText, maskType: MaskType?): TextWatcher {
        return object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val value = unmask(s.toString())
                val mask: String
                mask = when (maskType) {
                    MaskType.CPF -> maskCPF
                    MaskType.CEP -> maskCEP
                    MaskType.BIRTH -> maskBirthDate
                    MaskType.PHONE -> maskPhoneNumber

                    else -> ""
                }
                var maskAux = ""
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#') {
                        maskAux += m
                        continue
                    }
                    maskAux += try {
                        value[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                if (unmask(maskAux) != value || count > before) {
                    editText.setText(maskAux)
                    editText.setSelection(maskAux.length)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        }
    }

    enum class MaskType {
        CEP, CPF, BIRTH, PHONE
    }
}