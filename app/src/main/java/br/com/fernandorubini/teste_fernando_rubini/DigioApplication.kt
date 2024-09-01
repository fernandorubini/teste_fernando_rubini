package br.com.fernandorubini.teste_fernando_rubini

import android.app.Application
import android.content.ContentValues
import android.util.Log
import br.com.fernandorubini.teste_fernando_rubini.base.di.baseModule
import br.com.fernandorubini.teste_fernando_rubini.home.di.mainModule
import br.com.fernandorubini.teste_fernando_rubini.login.di.loginModule
import br.com.fernandorubini.teste_fernando_rubini.register.forms.di.registerModule
import br.com.fernandorubini.teste_fernando_rubini.register.starter.di.starterModule
import br.com.fernandorubini.teste_fernando_rubini.register.success.di.successModule
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DigioApplication : Application() {

    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
        initializeRemoteConfig()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@DigioApplication)
            androidLogger(Level.ERROR)
            modules(
                listOf(
                    mainModule,
                    loginModule,
                    registerModule,
                    successModule,
                    starterModule,
                    baseModule
                )
            )
        }
    }

    private fun initializeRemoteConfig() {
        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            // minimumFetchIntervalInSeconds = 3600
            minimumFetchIntervalInSeconds = 30
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(ContentValues.TAG, getString(R.string.remote_config_success))
            } else {
                Log.d(ContentValues.TAG, getString(R.string.remote_config_fail))
            }
        }
    }
}

