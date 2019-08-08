package br.com.rafaeldias.gym.utils

import java.text.DecimalFormat
import java.text.NumberFormat

class Format {
    companion object {
        fun Rating (valor: Double): String {
            return DecimalFormat("0.00").format(valor)
        }
    }
}