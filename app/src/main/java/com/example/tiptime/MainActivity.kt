package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        /**

         // Old way with findViewById()
         val myButton: Button = findViewById(R.id.my_button)
         myButton.text = "A button"

         // Better way with view binding
         val myButton: Button = binding.myButton
         myButton.text = "A Button"

         // Best way with view binding and no extra variable
         binding.myButton.text = "A Button"

         */
        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    fun calculateTip() {

        val stringCost =  binding.costOfService.text.toString()

        val cost = stringCost.toDoubleOrNull()

        if(cost == null) {
            binding.tipResult.text = " "
            return
        }

        val selectedId = binding.tipOptions.checkedRadioButtonId

        val tipPercentage = when(selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked

        if(roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}
