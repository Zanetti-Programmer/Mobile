package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pesoEditText = findViewById<EditText>(R.id.weight_edit_text)
        val alturaEditText = findViewById<EditText>(R.id.height_edit_text)
        val calcularButton = findViewById<Button>(R.id.calculate_button)

        calcularButton.setOnClickListener {
val pesoString = pesoEditText.text.toString().replace(',', '.')
val alturaString = alturaEditText.text.toString().replace(',', '.')

val peso = pesoString.toDoubleOrNull()
val altura = alturaString.toDoubleOrNull()


            if (peso != null && altura != null) {
                val imc = peso / (altura / 100.0).pow(2)
                val imcResultado = String.format("%.2f", imc)

                val classificacao = when (imc) {
                    in 0.8..18.5 -> "Abaixo do peso, você esta magro"
                    in 18.5..25.0 -> "Peso ideal"
                    in 25.0..30.0 -> "Acima do peso, cuidado!"
                    in 30.0..35.0 -> "Obesidade Grau I, Vai treinar porra!!"
                    in 35.0..40.0 -> "Obesidade Grau II Ainda da tempo..."
                    else -> "Obesidade Grau III poça de rolho"
                }

                mostrarDialogo(imcResultado, classificacao)
            } else {
                mostrarDialogo("Erro", "Por favor, insira valores válidos.")
            }
        }
    }

    private fun mostrarDialogo(imcResultado: String, classificacao: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Resultado do IMC")
        builder.setMessage("Seu IMC é $imcResultado, que é classificado como $classificacao.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
