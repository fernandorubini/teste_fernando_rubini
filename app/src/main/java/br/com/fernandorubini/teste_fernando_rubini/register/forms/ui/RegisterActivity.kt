package br.com.fernandorubini.teste_fernando_rubini.register.forms.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import br.com.fernandorubini.teste_fernando_rubini.R
import br.com.fernandorubini.teste_fernando_rubini.base.extensions.observeNotNull
import br.com.fernandorubini.teste_fernando_rubini.databinding.ActivityRegisterBinding
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.model.User
import br.com.fernandorubini.teste_fernando_rubini.register.success.ui.SuccessActivity
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants
import br.com.fernandorubini.teste_fernando_rubini.utils.MaskUtils
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.isValidCPF
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.isValidEmail
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.isValidName
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.isValidPhone
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.showMessage
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.validate
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupObservers()
        maskComponents()
        setupFieldValidation()
    }

    private fun setupFieldValidation() {
        binding.apply {
            etName.addTextChangedListener { validateButton() }
            etCpf.addTextChangedListener { validateButton() }
            etCell.addTextChangedListener { validateButton() }
            etEmail.addTextChangedListener { validateButton() }
            etPassword.addTextChangedListener { validateButton() }
        }
    }

    private fun setupObservers() {
        observeNotNull(viewModel.viewState) {
            when (it) {
                is RegisterViewState.NavigateSuccessScreen -> handleNavigateSuccessScreen(it.user)
                is RegisterViewState.OpenChallenger -> handleOpenChallenger()
                is RegisterViewState.NavigateBack -> handleNavigateBack()
                is RegisterViewState.NotifyError -> handleNotifyError(it.message)
            }
        }
    }

    private fun handleNotifyError(message: String) {
        showMessage(message)
    }

    private fun handleNavigateBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    private fun handleOpenChallenger() {
        Toast.makeText(this, getString(R.string.opened_recaptcha), Toast.LENGTH_SHORT).show()
    }

    private fun handleNavigateSuccessScreen(user: User) {
        val intent = Intent(this@RegisterActivity, SuccessActivity::class.java)
        intent.putExtra(Constants.NAME, user.name)
        intent.putExtra(Constants.EMAIL, user.email)
        intent.putExtra(Constants.CPF, user.cpf)
        startActivity(intent)
    }

    private fun maskComponents() {
        binding.apply {
            etCpf.addTextChangedListener(
                MaskUtils.insert(
                    binding.etCpf,
                    MaskUtils.MaskType.CPF
                ),

                )
            etCell.addTextChangedListener(
                MaskUtils.insert(
                    binding.etCell,
                    MaskUtils.MaskType.PHONE
                )
            )

        }
    }

    private fun getUserData(): User {
        return User(
            binding.etName.text.toString(),
            binding.etCpf.text.toString(),
            binding.etCell.text.toString(),
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
        )
    }

    private fun validateButton(): Boolean {
        binding.apply {
            val nameValid = etName.text.toString().isValidName()
            val cpfValid = etCpf.text.toString().isValidCPF()
            val phoneValid = etCell.text.toString().isValidPhone()
            val emailValid = etEmail.text.toString().isValidEmail()
            val passwordValid = etPassword.text.toString().isNotEmpty()

            btContinue.isEnabled =
                nameValid && cpfValid && phoneValid && emailValid && passwordValid
            return btContinue.isEnabled
        }
    }

    private fun setupListeners() {
        binding.apply {
            btBack.setOnClickListener {
                viewModel.dispatchViewAction(RegisterViewAction.ButtonBack)
            }
            btContinue.setOnClickListener {
                viewModel.dispatchViewAction(RegisterViewAction.ButtonContinue(getUserData()))
            }
            btNotRobot.setOnClickListener {
                viewModel.dispatchViewAction(RegisterViewAction.ReCaptcha)
            }
            etName.validate(
                itName,
                getString(R.string.enter_your_full_name),
            ) { s ->
                s.isValidName()
            }
            etCpf.validate(
                itCpf,
                getString(R.string.invalid_cpf),
            ) { s ->
                s.isValidCPF()
            }
            etCell.validate(
                itCell,
                getString(R.string.invalid_phone_number),
            ) { s ->
                s.isValidPhone()
            }
            etEmail.validate(
                itEmail,
                getString(R.string.err_email),
            ) { s ->
                s.isValidEmail()
            }

        }
    }
}