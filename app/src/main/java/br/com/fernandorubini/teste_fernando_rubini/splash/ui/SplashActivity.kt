package br.com.fernandorubini.teste_fernando_rubini.splash.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import br.com.fernandorubini.teste_fernando_rubini.databinding.ActivitySplashBinding
import br.com.fernandorubini.teste_fernando_rubini.register.starter.ui.StarterActivity
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, StarterActivity::class.java)
            startActivity(intent)
            finish()
        }, Constants.TWOTHOUSAND)
    }
}