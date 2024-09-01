package br.com.fernandorubini.teste_fernando_rubini.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import br.com.fernandorubini.teste_fernando_rubini.R
import br.com.fernandorubini.teste_fernando_rubini.databinding.DialogDefaultFragmentBinding
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.isNotNullOrBlank
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.visibility
import br.com.fernandorubini.teste_fernando_rubini.utils.extensions.visible
import java.util.Objects

class DialogDefaultFragment : DialogFragment() {

    private lateinit var binding: DialogDefaultFragmentBinding
    var listener: OnClickListener? = null
    private lateinit var transaction: FragmentTransaction

    private var showCloseButton = false
    private var actionCloseButton: (() -> Unit)? = null

    private var message: String? = null
    private var title: String? = null
    private var textButtonYes: String? = null
    private var textButtonNo: String? = null

    override fun onStart() {
        this.setCancelable(false)
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Objects.requireNonNull(dialog)?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        binding =
            DialogDefaultFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogStyle)
        initViews()
    }

    private fun initViews() {
        if (view != null) {
            with(binding) {
                closeButton.apply {
                    visibility(showCloseButton)
                    setOnClickListener {
                        actionCloseButton?.invoke()
                        dismiss()
                    }
                }
                if (title != null) {
                    textTitle.visibility = View.VISIBLE
                    textTitle.text = title
                }

                textMessage.apply {
                    text =
                        if (message.isNotNullOrBlank()) message
                        else getString(R.string.try_again)
                    visible()
                }

                if (textButtonYes != null) {
                    btPrimary.apply {
                        text = textButtonYes
                        visible()
                    }
                }
                if (textButtonNo != null) {
                    btSecondary.apply {
                        text = textButtonNo
                        visible()
                    }
                }

                if (listener != null) {
                    btPrimary.setOnClickListener {
                        dismiss()
                        listener?.primaryButtonListener(this@DialogDefaultFragment)
                    }
                    btSecondary.setOnClickListener {
                        dismiss()
                        listener?.secondaryButtonListener(this@DialogDefaultFragment)
                    }
                } else {
                    btPrimary.setOnClickListener { dismiss() }
                }
            }
        }
    }

    override fun show(transaction: FragmentTransaction, tag: String?): Int {
        this.transaction = transaction
        var returnShow = 0
        try {
            returnShow = super.show(transaction, tag)
        } catch (e: Exception) {
            dismissAllowingStateLoss()
        }
        return returnShow
    }

    override fun dismissAllowingStateLoss() {
        try {
            parentFragmentManager?.popBackStack(
                transaction.commitAllowingStateLoss(),
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            super.dismissAllowingStateLoss()
        } catch (e: Exception) {
            e.message
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            title: String?,
            message: String?,
            textButtomOne: String?,
            textButtomTwo: String?,
            oneButton: OnClickListener?,
            actionCloseButton: (() -> Unit)? = {},
            showCloseButton: Boolean = false,

            ): DialogDefaultFragment {
            val dialogFragment = DialogDefaultFragment()
            dialogFragment.title = title
            dialogFragment.message = message
            dialogFragment.textButtonYes = textButtomOne
            dialogFragment.textButtonNo = textButtomTwo
            dialogFragment.listener = oneButton
            dialogFragment.showCloseButton = showCloseButton
            dialogFragment.actionCloseButton = actionCloseButton
            return dialogFragment
        }
    }

    interface OnClickListener {
        fun primaryButtonListener(dialogFragment: DialogFragment?)
        fun secondaryButtonListener(dialogFragment: DialogFragment?)
    }
}