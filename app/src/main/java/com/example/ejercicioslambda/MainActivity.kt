package com.example.ejercicioslambda

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val esPrimo:(Int) -> Boolean = esPrimo@{
            for (i in 2..it/2) {
                if (it % i == 0) {
                    return@esPrimo false

                }
            }

            true

        }

        val esMagico:(Int) -> Boolean = {
            var numElevado: Int = it.toDouble().pow(3).toInt()
            var sumaDig: Int = 0

            while (numElevado > 0) {
                sumaDig += numElevado%10
                numElevado /= 10

            }
            (sumaDig==it)

        }

        val esCapicua:(num: Int) -> Boolean = {
            val numInv: Int = it.toString().reversed().toInt()
            (numInv == it)
        }





        fun filtArray(lista: Array<Int>, f: (Int) -> Boolean): ArrayList<Int> {
            val listaSalida: ArrayList<Int> = arrayListOf()
            for (num in lista) {
                if (f(num)) listaSalida.add(num)

            }

            return listaSalida
        }

        val lista: Array<Int> = arrayOf(1, 2, 6, 11, 17, 121)

        val listaOriginal: TextView = findViewById(R.id.listaOriginal)
        val listaSalida: TextView = findViewById(R.id.ListaSalida)
        val grupoRadioB: RadioGroup = findViewById(R.id.grupoRadioB)
        val filtrar: Button = findViewById(R.id.botonFiltrar)


        filtrar.setOnClickListener {
            when (grupoRadioB.checkedRadioButtonId) {
                R.id.radioButton -> listaSalida.text = filtArray(lista,esPrimo).toString()
                R.id.radioButton2 -> listaSalida.text = filtArray(lista, esMagico).toString()
                R.id.radioButton3 -> listaSalida.text = filtArray(lista, esCapicua).toString()

            }
        }

        listaOriginal.text = lista.contentToString()

    }
}