package br.com.fernandorubini.teste_fernando_rubini.base.utils.moshi

interface JsonMoshiConverter {

    fun <T> toJson(data: T, clazz: Class<T>): String

    fun <T> fromJson(json: String, clazz: Class<T>): T?

    fun <T> toJsonList(data: List<T>, clazz: Class<T>): String

    fun <T> fromJsonList(json: String, clazz: Class<T>): List<T>?
}