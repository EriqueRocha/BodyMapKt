package com.example.bodymapkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodymapkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = MainAdapter()

       binding.rvMain.adapter = adapter //informa qual layout que vai inflar, quantos item e como renderizar

        //posição dos elementos
        binding.rvMain.layoutManager = LinearLayoutManager(this)

    }
    //informando que está classe será a classe que vai adaptar a recyclerview, o que será adaptado no adapter
    // e para isso precisará estender de RecyclerView.Adapter
    private inner class MainAdapter: RecyclerView.Adapter<MainViewHolder>(){

        //informa o layout xml da célula especifica, o item
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }
        // é acionado quando a tela rola etem q trocar o conteudo das celulas que estão aparecendo
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        }

        // retorna um inteiro pra informar quantas celulas a lista terá
        override fun getItemCount(): Int {
            return 15
        }

    }

    //classe que informa a celula especifica, que mostra cada célula
    //esta classe espera uma view e estende de RecyclerView.ViewHolder
    private class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)
}