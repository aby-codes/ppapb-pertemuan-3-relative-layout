package com.example.pertemuan3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.pertemuan3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var hasilHitung = 0.0
    private var operatorVar = "+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editText = binding.textInput
        editText.requestFocus()
        editText.showSoftInputOnFocus = false

        val numberButtons = listOf(
            binding.button0, binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6, binding.button7,
            binding.button8, binding.button9
        )

        for (i in 0..9) {
            setButtonClickListener(numberButtons[i], i.toString())
        }

        binding.button0.setOnClickListener {
            insertNumber("0")
        }

        val operatorButtons = listOf(
            binding.buttonPlus, binding.buttonMinus, binding.buttonPer, binding.buttonTimes, binding.buttonEquals
        )

        val operatorSymbols = listOf("+", "-", "/", "*","=")

        operatorButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                handleOperator(operatorSymbols[index])
            }
        }

        binding.clear.setOnClickListener {
            clearAll()
        }
    }

    private fun setButtonClickListener(button: Button, newNumber: String) {
        button.setOnClickListener {
            insertNumber(newNumber)
        }
    }

    private fun insertNumber(newNumber: String) {
        val caretPosition: Int = binding.textInput.selectionStart
        val updatedText = StringBuilder(binding.textInput.text.toString())
        updatedText.insert(caretPosition, newNumber)
        binding.textInput.setText(updatedText.toString())
        binding.textInput.setSelection(caretPosition + 1)
    }

    private fun handleOperator(newOperator: String) {
        if (newOperator == "=") {
            try {
                when (operatorVar) {
                    "+" -> hasilHitung += binding.textInput.text.toString().toDouble()
                    "-" -> hasilHitung -= binding.textInput.text.toString().toDouble()
                    "/" -> hasilHitung /= binding.textInput.text.toString().toDouble()
                    "*" -> hasilHitung *= binding.textInput.text.toString().toDouble()
                }
            } catch (e: NumberFormatException) {
            }

            binding.operator.text = ""
            binding.textInput.setText(hasilHitung.toString())
            binding.textHasil.text = ""
            binding.textHasil.visibility = View.GONE
        } else {
            try {
                when (operatorVar) {
                    "+" -> hasilHitung += binding.textInput.text.toString().toDouble()
                    "-" -> hasilHitung -= binding.textInput.text.toString().toDouble()
                    "/" -> hasilHitung /= binding.textInput.text.toString().toDouble()
                    "*" -> hasilHitung *= binding.textInput.text.toString().toDouble()
                }
            } catch (e: NumberFormatException) {
            }

            operatorVar = newOperator
            binding.operator.text = newOperator
            binding.textInput.setText("")
            binding.textHasil.text = hasilHitung.toString()
            binding.textHasil.visibility = View.VISIBLE
        }
    }


    private fun clearAll() {
        binding.textInput.setText("")
        hasilHitung = 0.0
        operatorVar = "+"
        binding.operator.text = ""
        binding.textHasil.visibility = View.GONE
    }
}
