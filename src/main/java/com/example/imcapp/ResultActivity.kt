package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var tvResult:TextView
    private lateinit var numero:TextView
    private lateinit var descripcion:TextView
    private lateinit var recalcular:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result:Double = intent.extras?.getDouble("imcResult") ?: -1.0

        initializer()
        initUI(result)
        listeners()
    }

    private fun listeners() {
        recalcular.setOnClickListener { navigateBack() }
    }

    private fun navigateBack() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initUI(result:Double) {
        numero.text = result.toString()
        when(result){
            in 0.00..18.5 -> {
                tvResult.text = getString(R.string.f)
                descripcion.text = getString(R.string.df)
            }
            in 18.51..24.99 -> {
                tvResult.text = getString(R.string.n)
                descripcion.text = getString(R.string.dn)
            }
            in 25.0..29.99 -> {
                tvResult.text = getString(R.string.s)
                descripcion.text = getString(R.string.ds)
            }
            in 30.00..99.99 -> {
                tvResult.text = getString(R.string.o)
                descripcion.text = getString(R.string.dob)
            }
            else -> {
                numero.text = getString(R.string.ERROR)
                tvResult.text = getString(R.string.ERROR)
                descripcion.text = getString(R.string.ERROR)
            }
        }
    }

    private fun initializer() {
        tvResult = findViewById(R.id.tvResult)
        numero = findViewById(R.id.numero)
        descripcion = findViewById(R.id.descripcion)
        recalcular = findViewById(R.id.recalcular)
    }
}