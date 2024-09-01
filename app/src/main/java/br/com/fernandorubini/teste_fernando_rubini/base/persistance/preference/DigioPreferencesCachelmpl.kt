package br.com.fernandorubini.teste_fernando_rubini.base.persistance.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.fernandorubini.teste_fernando_rubini.base.utils.moshi.JsonMoshiConverter


class DigioPreferencesCachelmpl (
    private val jsonMoshiConverter: JsonMoshiConverter,
    private val encryptedPreferences: SharedPreferences
    ) : DigioPreferencesCache
    {

        override fun putString(key: String, data: String) {
            encryptedPreferences.edit {
                this.putString(key, data)
                this.commit()
            }
        }

        override fun getString(key: String, defaultValue: String): String {
            return encryptedPreferences.getString(key, defaultValue) ?: defaultValue
        }

        override fun putBoolean(key: String, data: Boolean) {
            encryptedPreferences.edit {
                this.putBoolean(key, data)
                this.commit()
            }
        }

        override fun getBoolean(key: String): Boolean {
            return encryptedPreferences.getBoolean(key, false)
        }

        override fun <T> putData(key: String, data: T, clazz: Class<T>) {
            this.putString(key, jsonMoshiConverter.toJson(data, clazz))
        }

        override fun <T> getData(key: String, clazz: Class<T>): T? {
            val json = getString(key)
            return jsonMoshiConverter.fromJson(json, clazz)
        }

        override fun <T> putList(key: String, data: List<T>, clazz: Class<T>) {
            putString(key, jsonMoshiConverter.toJsonList(data, clazz))
        }

        override fun <T> getList(key: String, clazz: Class<T>): List<T>? {
            val json = getString(key)
            return jsonMoshiConverter.fromJsonList(json, clazz)
        }

        override fun clear(key: String) {
            encryptedPreferences.edit {
                this.remove(key)
                this.commit()
            }
        }

        override fun contains(key: String): Boolean {
            return encryptedPreferences.contains(key)
        }
    }