package br.com.fernandorubini.teste_fernando_rubini.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fernandorubini.teste_fernando_rubini.R
import br.com.fernandorubini.teste_fernando_rubini.base.extensions.observeNotNull
import br.com.fernandorubini.teste_fernando_rubini.databinding.ActivityLoginBinding
import br.com.fernandorubini.teste_fernando_rubini.home.ui.MainActivity
import br.com.fernandorubini.teste_fernando_rubini.register.forms.ui.RegisterActivity
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.isValidEmail
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.showMessage
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.validate
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    private lateinit var email: String
    private lateinit var password: String
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupObservers()
        firebaseAuth.signOut()
    }

    override fun onStart() {
        super.onStart()
        verifyUserLogger()
    }

    private fun verifyUserLogger() {
        val currentUserLogged = firebaseAuth.currentUser
        if (currentUserLogged != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }

    private fun setupObservers() {
        observeNotNull(viewModel.viewState) {
            when (it) {
                is LoginViewEstate.NavigateHome -> handleNavigateHome()
                is LoginViewEstate.NavigateBack -> handleNavigateBack()
                is LoginViewEstate.NavigateRegister -> handleNavigateRegister()
                is LoginViewEstate.NotifyError -> handleNotifyError(it.message)
                else -> {}
            }

        }
    }

    private fun handleNotifyError(message: String) {
        showMessage(message)
    }

    private fun handleNavigateHome() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }

    private fun handleNavigateBack() {
        onBackPressed()
    }

    private fun handleNavigateRegister() {
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))

    }


    private fun setupListeners() {
        binding.apply {
            btBack.setOnClickListener {
                viewModel.dispatchViewAction(LoginViewAction.ButtonBack)
            }
            btContinue.setOnClickListener {
                if (validateFields()) {
                    val email = binding.etLogin.text.toString()
                    val password = binding.etPassword.text.toString()
                    viewModel.dispatchViewAction(LoginViewAction.ButtonContinue(email, password))
                }
            }
            btRegister.setOnClickListener {
                viewModel.dispatchViewAction(LoginViewAction.ButtonRegister)
            }
            etLogin.validate(
                itLogin,
                getString(R.string.err_email),
            ) { s -> s.isValidEmail() }
        }
    }

    private fun validateFields(): Boolean {
        email = binding.etLogin.text.toString()
        password = binding.etPassword.text.toString()
        if (email.isNotEmpty()) {
            binding.itLogin.error = null
            if (password.isNotEmpty()) {
                binding.itPassword.error = null
            } else {
                binding.itPassword.error = getString(R.string.fill_in_your_password)
                return false
            }
        } else {
            binding.itLogin.error = getString(R.string.fill_your_email)
            return false
        }
        return true
    }
}