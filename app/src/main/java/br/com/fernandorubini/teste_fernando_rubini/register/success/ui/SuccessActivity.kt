package br.com.fernandorubini.teste_fernando_rubini.register.success.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fernandorubini.teste_fernando_rubini.base.extensions.observeNotNull
import br.com.fernandorubini.teste_fernando_rubini.databinding.ActivitySuccessBinding
import br.com.fernandorubini.teste_fernando_rubini.home.ui.MainActivity
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding
    private val viewModel: SuccessViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupObservers()
    }


    private fun setupObservers() {
        observeNotNull(viewModel.viewState) {
            when (it) {
                is SuccessViewEstate.NavigateHome -> handleNavigateHome(
                    it.name,
                    it.email,
                    it.cpf
                )
            }
        }
    }


    private fun handleNavigateHome(name: String?, email: String?, cpf: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Constants.NAME, name)
        intent.putExtra(Constants.EMAIL, email)
        intent.putExtra(Constants.CPF, cpf)
        startActivity(intent)
        finish()
    }

    private fun setupListeners() {

        binding.btStart.setOnClickListener {
            val name = intent.getStringExtra(Constants.NAME)
            val email = intent.getStringExtra(Constants.EMAIL)
            val cpf = intent.getStringExtra(Constants.CPF)
            viewModel.dispatchViewAction(SuccessViewAction.ButtonToStart(name, email, cpf))
        }
    }
}