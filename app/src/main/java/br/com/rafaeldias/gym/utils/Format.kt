package br.com.rafaeldias.gym.utils

import java.text.DecimalFormat

class Format {
    companion object {
        fun Rating(valor: Double): String {
            return DecimalFormat("0.00").format(valor)
        }
    }
}