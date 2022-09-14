package com.example.bodymapkt

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import com.example.bodymapkt.databinding.ActivityImcBinding

class ImcActivity : AppCompatActivity() {

    private val binding by lazy { ActivityImcBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnImcSend.setOnClickListener{
        if (!validate()){
            Toast.makeText(this, R.string.fields_message, Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
        //sucesso
            val weight = binding.editImcWeight.text.toString().toInt()
            val height = binding.editImcHeight.text.toString().toInt()
            val result = calculateImc(weight, height)
            Log.d("teste", "resultado: $result")

            val imcResponseId =imcResponse(result)

            val dialog = AlertDialog.Builder(this)

            .setTitle(getString(R.string.imc_response, result))
            .setMessage(imcResponseId)
            .setPositiveButton(android.R.string.ok
            ) { dialog, which ->

            }
            val d=dialog.create()
            d.show()
        }
    }

    //as classes que derivam de um XML tem seus valores representados por numero inteiro
    //por isso a função retorna um Int mesmo mostrando um texto

    @StringRes // força que o retorno sempre seja um recurso do Android, no caso, um recursoo do R.string
    private fun imcResponse(imc: Double): Int{
        return when{
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }
    private fun calculateImc(weight: Int, height: Int): Double{
        return weight/ ((height/100.0)*(height/100.0))
    }
    private fun validate(): Boolean{ // a função vai retornar um boleano
        if (binding.editImcWeight.toString().isNotEmpty()
            && binding.editImcHeight.toString().isNotEmpty()
            && !binding.editImcWeight.toString().startsWith("0")
            && !binding.editImcHeight.toString().startsWith("0")){
            return true
        }
            return false
    }
}