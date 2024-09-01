package br.com.fernandorubini.teste_fernando_rubini.base.di

import android.content.Context
import android.content.SharedPreferences
import br.com.fernandorubini.teste_fernando_rubini.base.persistance.memory.DigioMemoryCache
import br.com.fernandorubini.teste_fernando_rubini.base.persistance.memory.DigioMemoryCachelmpl
import br.com.fernandorubini.teste_fernando_rubini.base.persistance.preference.DigioPreferencesCache
import br.com.fernandorubini.teste_fernando_rubini.base.persistance.preference.DigioPreferencesCachelmpl
import br.com.fernandorubini.teste_fernando_rubini.base.utils.moshi.JsonMoshiConverter
import br.com.fernandorubini.teste_fernando_rubini.base.utils.moshi.JsonMoshiConverterImpl
import br.com.fernandorubini.teste_fernando_rubini.base.utils.moshi.MoshiFactory
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val baseModule = module {
    single { MoshiFactory.build() }
    factory<DigioMemoryCache> { DigioMemoryCachelmpl(get()) }
    factory<DigioPreferencesCache> { DigioPreferencesCachelmpl(get(), get()) }
    factory<JsonMoshiConverter> { JsonMoshiConverterImpl(get()) }
    factory<SharedPreferences> {
        androidContext().getSharedPreferences(
            Constants.DIGIO_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }
}