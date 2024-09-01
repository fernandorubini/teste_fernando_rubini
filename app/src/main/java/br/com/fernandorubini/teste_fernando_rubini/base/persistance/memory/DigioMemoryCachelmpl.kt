package br.com.fernandorubini.teste_fernando_rubini.base.persistance.memory

import br.com.fernandorubini.teste_fernando_rubini.base.utils.moshi.JsonMoshiConverter

internal class DigioMemoryCachelmpl(private val jsonMoshiConverter: JsonMoshiConverter) :
    DigioMemoryCache {

    private val digioCache = mutableMapOf<String, String>()

    override fun putString(key: String, data: String) {
        digioCache[key] = data
    }

    override fun getString(key: String, defaultValue: String): String {
        return digioCache[key] ?: defaultValue
    }

    override fun putBoolean(key: String, data: Boolean) {
        digioCache[key] = data.toString()
    }

    override fun getBoolean(key: String): Boolean {
        return digioCache[key]?.toBoolean() ?: false
    }

    override fun <T> putData(key: String, data: T, clazz: Class<T>) {
        digioCache[key] = jsonMoshiConverter.toJson(data, clazz)
    }

    override fun <T> getData(key: String, clazz: Class<T>): T? {
        val json = digioCache[key]
        return jsonMoshiConverter.fromJson(json.orEmpty(), clazz)
    }

    override fun <T> putList(key: String, data: List<T>, clazz: Class<T>) {
        digioCache[key] = jsonMoshiConverter.toJsonList(data, clazz)
    }

    override fun <T> getList(key: String, clazz: Class<T>): List<T>? {
        val json = digioCache[key]
        return jsonMoshiConverter.fromJsonList(json.orEmpty(), clazz)
    }

    override fun clear(key: String) {
        digioCache.remove(key)
    }

    override fun contains(key: String): Boolean {
        return digioCache.contains(key)
    }
}