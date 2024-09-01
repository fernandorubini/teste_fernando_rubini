package br.com.fernandorubini.teste_fernando_rubini.home.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.fernandorubini.teste_fernando_rubini.R
import br.com.fernandorubini.teste_fernando_rubini.base.extensions.observeNotNull
import br.com.fernandorubini.teste_fernando_rubini.databinding.ActivityMainBinding
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.CashModel
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.DigioProductModel
import br.com.fernandorubini.teste_fernando_rubini.home.ui.adapter.ProductAdapter
import br.com.fernandorubini.teste_fernando_rubini.home.ui.adapter.SpotlightAdapter
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.unMask
import br.com.fernandorubini.teste_fernando_rubini.widget.DialogDefaultFragment
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()
    private var userDocument: String = String()
    private var userNameValue: String = String()
    private var userEmailValue: String = String()
    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter()
    }

    private val spotlightAdapter: SpotlightAdapter by lazy {
        SpotlightAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtras()
        setupObservers()
        setupView()
        remoteConfig()
        viewModel.dispatchViewAction(MainActivityViewAction.LoadData)
    }

    private fun getExtras() {
        val extras = intent.extras
        if (extras != null) {
            userNameValue = extras.getString(Constants.NAME, String()).orEmpty()
            userEmailValue = extras.getString(Constants.EMAIL, String()).orEmpty()
            userDocument = extras.getString(Constants.CPF, String()).orEmpty().unMask()
        }
    }

    private fun remoteConfig() {
        val showProductRw: Boolean = Firebase.remoteConfig.getBoolean(Constants.SHOW_PRODUCTS)
        val showSpotlights: Boolean = Firebase.remoteConfig.getBoolean(Constants.SHOW_SPOTLIGHT)
        if (showProductRw) {
            binding.recyMainProducts.visibility = View.VISIBLE
            binding.txtMainProducts.visibility = View.VISIBLE
        }
        if (showSpotlights) {
            binding.recyMainSpotlight.visibility = View.VISIBLE
        }
    }

    private fun setupView() {
        binding.apply {
            recyMainProducts.adapter = productAdapter
            recyMainSpotlight.adapter = spotlightAdapter
            title.text = userNameValue
        }
    }

    private fun setupObservers() {
        observeNotNull(viewModel.viewState) {
            when (it) {
                is MainActivityViewState.LoadingData -> handleLoadingData(it.digioProductModel)
                MainActivityViewState.Error -> handleError()
                MainActivityViewState.ShowLoading -> handleShowLoading()
                MainActivityViewState.HideLoading -> handleHideLoading()
            }
        }
    }

    private fun handleHideLoading() {
        binding.loadDigioContainer.progressDigio.visibility = View.GONE
    }

    private fun handleShowLoading() {
        binding.loadDigioContainer.progressDigio.visibility = View.VISIBLE
    }

    private fun handleError() {
        DialogDefaultFragment.newInstance(
            title = getString(R.string.caution),
            message = getString(R.string.error),
            textButtomOne = getString(R.string.ok_understand),
            textButtomTwo = null,
            oneButton = null,
            showCloseButton = true
        ).show(supportFragmentManager, TAG_MAIN_ACTIVITY)
    }

    private fun handleLoadingData(digioProductModel: DigioProductModel) {
        productAdapter.productModels = digioProductModel.productsModel
        spotlightAdapter.spotlightModels = digioProductModel.spotlightsModel
        setupDigioCashText(digioProductModel.cashModel)
        binding.body.isVisible = true
    }

    private fun setupDigioCashText(cashModel: CashModel) {
        val digioCashText = cashModel.title.ifEmpty { getString(R.string.digio_cash) }
        if (digioCashText.length > Constants.FIVE) setSpannable(digioCashText)

    }

    private fun setSpannable(text: String) {
        binding.txtMainDigioCash.text = SpannableString(text).apply {
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.blue_darker)
                ),
                Constants.ZERO,
                Constants.FIVE,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.font_color_digio_cash)
                ),
                Constants.SIX,
                text.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    companion object {
        private const val TAG_MAIN_ACTIVITY = "TAG_MAIN_ACTIVITY"
    }
}