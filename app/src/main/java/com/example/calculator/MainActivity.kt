package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.Calculations.Companion.calculateResults

class MainActivity : AppCompatActivity() {
    private lateinit var workingsTV: TextView
    private lateinit var resultsTV: TextView
    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workingsTV = findViewById(R.id.workingsTV)
        resultsTV = findViewById(R.id.resultsTV)
    }

    fun numberAction(view: View) {
        if (view is Button) {
            if (view.text == ".") {
                if (canAddDecimal) {
                    workingsTV.append(view.text)
                }
                canAddDecimal = false
            }
            else {
                workingsTV.append(view.text)
            }
            canAddOperation = true
        }
    }
    fun operationAction(view: View) {
        if (view is Button && canAddOperation) {
            workingsTV.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }
    fun allClearAction(view: View) {
        workingsTV.text = ""
        resultsTV.text = ""
    }
    fun backSpaceAction(view: View) {
        val length = workingsTV.length()
        if(length > 0) {
            workingsTV.text = workingsTV.text.subSequence(0, length-1)
        }
    }
    fun equalsAction(view: View) {
        resultsTV.text = calculateResults(workingsTV.text)
    }
}