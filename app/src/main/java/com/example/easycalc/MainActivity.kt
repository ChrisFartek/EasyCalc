package com.example.easycalc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var invalidError = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        if(invalidError != 0 || tvOut.text == "0") {
            tvOut.text = ""
            invalidError = 0
            tvOut.append((view as Button).text)

        }
        else {
            tvOut.append((view as Button).text)
        }
    }

    fun clear(view: View) {
        tvOut.text = ""
    }

    fun onOperator(view: View) {
        if(!isOperatorAdded(tvOut.text.toString())) {
            tvOut.append((view as Button).text)
        }
    }

    @SuppressLint("SetTextI18n")
    fun onSolve(view: View) {
        var tvValue = tvOut.text.toString()
        var prefix = ' '

        if(tvValue.endsWith('+') || tvValue.endsWith('-') || tvValue.endsWith('*') || tvValue.endsWith('/')) {
            tvOut.text = "Insert valid formula"
            invalidError = 1
            return
        }
        try {
            var result = 0
            if(tvValue.startsWith('-')) {
                prefix = '-'
                tvValue = tvValue.substring(1)
            }

            if(tvValue.contains('-')) {
                val splitValue = tvValue.split('-')

                var one = splitValue[0]
                var two = splitValue[1]

                if(prefix == '-') {
                    one = prefix + one
                }
                result = one.toInt() - two.toInt()
            }
            else if(tvValue.contains('+')) {
                val splitValue = tvValue.split('+')

                var one = splitValue[0]
                var two = splitValue[1]

                if(prefix == '-') {
                    one = prefix + one
                }

                result = one.toInt() + two.toInt()
            }
            else if(tvValue.contains('*')) {
                val splitValue = tvValue.split('*')

                var one = splitValue[0]
                var two = splitValue[1]

                if(prefix == '-') {
                    one = prefix + one
                }

                result = one.toInt() * two.toInt()
            }
            else if(tvValue.contains('/')) {
                val splitValue = tvValue.split('/')

                var one = splitValue[0]
                var two = splitValue[1]

                if(prefix == '-') {
                    one = prefix + one
                }

                result = one.toInt() / two.toInt()
            }

            tvOut.text = result.toString()
        }
        catch (e: ArithmeticException) {
            e.printStackTrace()
        }
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if (value.startsWith('-')) {
            false
        } else {
            value.contains('/') || value.contains('*') || value.contains('+') || value.contains('-')
        }
    }
}