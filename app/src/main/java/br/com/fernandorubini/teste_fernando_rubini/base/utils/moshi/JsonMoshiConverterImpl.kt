package br.com.fernandorubini.teste_fernando_rubini.base.utils.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class JsonMoshiConverterImpl(private val moshi: Moshi) : JsonMoshiConverter {

        override fun <T> toJson(data: T, clazz: Class<T>): String {
            val adapter = moshi.adapter(clazz)
            return runCatching { adapter.toJson(data) }.getOrNull().orEmpty()
        }

        override fun <T> fromJson(json: String, clazz: Class<T>): T? {
            val adapter = moshi.adapter(clazz)
            return runCatching { adapter.fromJson(json) }.getOrNull()
        }

        override fun <T> toJsonList(data: List<T>, clazz: Class<T>): String {
            return runCatching {
                val adapter = getAdapterList(clazz)
                adapter.toJson(data)
            }.getOrDefault(String())
        }

        override fun <T> fromJsonList(json: String, clazz: Class<T>): List<T>? {
            return runCatching {
                val adapter = getAdapterList(clazz)
                adapter.fromJson(json)
            }.getOrNull()
        }

        private fun <T> getAdapterList(clazz: Class<T>): JsonAdapter<List<T>> {
            val type = Types.newParameterizedType(List::class.java, clazz)
            return moshi.adapter(type)
        }
    }