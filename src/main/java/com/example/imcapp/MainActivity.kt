package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var hombre:CardView
    private lateinit var mujer:CardView
    private lateinit var height:TextView
    private lateinit var slider:RangeSlider
    private lateinit var menosPeso:FloatingActionButton
    private lateinit var masPeso:FloatingActionButton
    private lateinit var peso:TextView
    private lateinit var edad:TextView
    private lateinit var masEdad:FloatingActionButton
    private lateinit var menosEdad:FloatingActionButton
    private lateinit var calcular:Button

    private var maleSelected:Boolean = true
    private var femaleSelected:Boolean = false
    private var pesoActual:Int = 70
    private var edadActual:Int = 25
    private var alturaActual:Int = 120

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializer()
        listener()
        initUI()
    }

    private fun setGender(){
        maleSelected = !maleSelected
        femaleSelected = !femaleSelected
    }

    private fun initUI() {
        setColor()
        setPeso()
        setEdad()
    }

    private fun listener() {
        hombre.setOnClickListener {
            setGender()
            setColor()
        }
        mujer.setOnClickListener {
            setGender()
            setColor()
        }
        slider.addOnChangeListener {  _,value,_ ->
            val df = DecimalFormat("#.##")
            alturaActual = df.format(value).toInt()
            height.text = "$alturaActual cm"
        }
        masPeso.setOnClickListener {
            pesoActual += 1
            setPeso()
        }
        menosPeso.setOnClickListener {
            pesoActual -= 1
            setPeso()
        }
        masEdad.setOnClickListener {
            edadActual += 1
            setEdad()
        }
        menosEdad.setOnClickListener {
            edadActual -= 1
            setEdad()
        }
        calcular.setOnClickListener {
            val result = calculateImc()
            navigate(result)
        }
        }

    private fun navigate(result: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("imcResult", result)
        startActivity(intent)
    }

    private fun calculateImc():Double {
        val df = DecimalFormat("#.##")
        return df.format(pesoActual/((alturaActual.toDouble()/100) * (alturaActual.toDouble()/100))).toDouble()
    }

    private fun setEdad() {
        edad.text = edadActual.toString()
    }

    private fun setPeso() {
        peso.text = pesoActual.toString()
    }

    private fun setColor() {
        hombre.setBackgroundColor(getBackgrounColor(maleSelected))
        mujer.setBackgroundColor(getBackgrounColor(femaleSelected))
    }

    private fun initializer() {
        hombre = findViewById(R.id.hombre)
        mujer = findViewById(R.id.mujer)
        height = findViewById(R.id.height)
        slider = findViewById(R.id.sliderHeight)
        menosPeso = findViewById(R.id.menosPeso)
        masPeso = findViewById(R.id.masPeso)
        peso = findViewById(R.id.tvPeso)
        edad = findViewById(R.id.tvEdad)
        masEdad = findViewById(R.id.masEdad)
        menosEdad = findViewById(R.id.menosEdad)
        calcular = findViewById(R.id.calcular)
    }

    private fun getBackgrounColor(isSelected:Boolean): Int {
        val colorReference = if(isSelected){
            R.color.background_component_selected
        } else{
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }
}