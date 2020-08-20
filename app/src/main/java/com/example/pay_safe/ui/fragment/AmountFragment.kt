package com.example.pay_safe.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pay_safe.ui.activity.MainActivity
import com.example.pay_safe.R
import com.example.pay_safe.ui.utils.closeSoftKeyBoard
import kotlinx.android.synthetic.main.fragment_amount.button_continue
import kotlinx.android.synthetic.main.fragment_amount.edit_text_amount
import java.text.NumberFormat
import java.util.Locale

class AmountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formatInstance =
            NumberFormat.getCurrencyInstance(Locale.US).also { it.maximumFractionDigits = 0 }

        button_continue.setOnClickListener {
            if (edit_text_amount.text.isNotEmpty()) {
                closeSoftKeyBoard()
                (activity as (MainActivity)).moveNext()
            } else {
                Toast.makeText(requireContext(), getString(R.string.amount_error_msg), Toast.LENGTH_SHORT).show()
            }
        }

        val moneyWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Keyboard is out off the screen
                val cleanString: String = p0.toString().replace("[$,.]".toRegex(), "")
                if (cleanString.isNotEmpty()) {
                    edit_text_amount.removeTextChangedListener(this)
                    val parsed = cleanString.toInt()
                    val formatted: String = formatInstance.format(parsed).replace(",", ".")
                    edit_text_amount.setText(formatted)
                    edit_text_amount.setSelection(formatted.length)
                    edit_text_amount.addTextChangedListener(this)

                } else if (p0?.isNotEmpty() == true) {
                    edit_text_amount.removeTextChangedListener(this)
                    edit_text_amount.setText("")
                    edit_text_amount.addTextChangedListener(this)
                }
            }
        }
        edit_text_amount.addTextChangedListener(moneyWatcher)
    }

    companion object {
//        fun newInstance() =
//            AmountFragment().apply {
//                arguments = Bundle().apply {
//                }
//            }
    }
}