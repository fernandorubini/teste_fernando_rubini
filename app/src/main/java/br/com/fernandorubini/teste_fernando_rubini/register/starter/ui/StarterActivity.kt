package br.com.fernandorubini.teste_fernando_rubini.register.starter.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fernandorubini.teste_fernando_rubini.base.extensions.observeNotNull
import br.com.fernandorubini.teste_fernando_rubini.databinding.ActivityStarterBinding
import br.com.fernandorubini.teste_fernando_rubini.login.ui.LoginActivity
import br.com.fernandorubini.teste_fernando_rubini.register.forms.ui.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStarterBinding
    private val viewModel: StarterViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        observeNotNull(viewModel.viewState) {
            when (it) {
                is StarterViewState.NavigateStater -> handleNavigateStater()
                is StarterViewState.NavigateHome -> handleNavigateHome()
            }
        }
    }

    private fun handleNavigateHome() {
        startActivity(Intent(this@StarterActivity, LoginActivity::class.java))
    }

    private fun handleNavigateStater() {
        startActivity(Intent(this@StarterActivity, RegisterActivity::class.java))
    }

    private fun setupListeners() {
        binding.apply {
            btStarter.setOnClickListener {
                viewModel.dispatchViewAction(StarterViewAction.ButtonStarter)
            }
            btAlready.setOnClickListener {
                viewModel.dispatchViewAction(StarterViewAction.ButtonAlready)
            }
        }
    }
}