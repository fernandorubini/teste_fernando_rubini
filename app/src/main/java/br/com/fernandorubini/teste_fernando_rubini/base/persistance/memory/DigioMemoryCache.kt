package br.com.fernandorubini.teste_fernando_rubini.base.persistance.memory

interface DigioMemoryCache {
    fun putString(key: String, data: String)

    fun getString(key: String, defaultValue: String = String()): String

    fun putBoolean(key: String, data: Boolean)

    fun getBoolean(key: String): Boolean

    fun <T> putData(key: String, data: T, clazz: Class<T>)

    fun <T> getData(key: String, clazz: Class<T>): T?

    fun <T> putList(key: String, data: List<T>, clazz: Class<T>)

    fun <T> getList(key: String, clazz: Class<T>): List<T>?

    fun clear(key: String)

    fun contains(key: String): Boolean
}