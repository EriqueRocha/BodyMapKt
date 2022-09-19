package com.example.bodymapkt

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodymapkt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mainItens = mutableListOf<MainItem>() //coleção de dados do tipo do objeto(dataclass)
        mainItens.add( //adicionei um elemento a lista e vou passalo ao adapter
            MainItem(id = 1,
            drawbleId = R.drawable.ic_imc,
            textStringId = R.string.label_imc,
            color = R.color.fundo_claro
                )
        )
        mainItens.add(
            MainItem(id = 2,
                drawbleId = R.drawable.ic_tmb,
                textStringId = R.string.tmb,
                color = Color.BLUE
            )
        )
        val adapter = MainAdapter(mainItens,object: OnItemClickListener{
            override fun onClick(id: Int) {
                when(id){
                    1 -> {
                        val intent = Intent(this@MainActivity, ImcActivity::class.java)
                        startActivity(intent)
                    }
                    2 -> { }
                }
                Log.i("teste", "foi")
            }
        })

       binding.rvMain.adapter = adapter //informa qual layout que vai inflar, quantos item e como renderizar

        //posição dos elementos
        binding.rvMain.layoutManager = GridLayoutManager(this, 2)

    }
    //informando que está classe será a classe que vai adaptar a recyclerview, o que será adaptado no adapter
    // e para isso precisará estender de RecyclerView.Adapter
    private inner class MainAdapter(
        private val mainItems: List<MainItem>,
        private val onItemClickListener: OnItemClickListener
        ): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

        //informa o layout xml da célula especifica, o item
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }
        // é acionado quando a tela rola etem q trocar o conteudo das celulas que estão aparecendo
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItems[position]
            holder.bind(itemCurrent)
        }

        // retorna um inteiro pra informar quantas celulas a lista terá
        override fun getItemCount(): Int {
            return mainItems.size
        }
        //classe que informa a celula especifica, que mostra cada célula
        //esta classe espera uma view e estende de RecyclerView.ViewHolder
        private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bind(item: MainItem){

                val img: ImageView = itemView.findViewById(R.id.item_img_icon)
                val name: TextView = itemView.findViewById(R.id.item_txt_name)
                val container: LinearLayout = itemView.findViewById(R.id.item_color_container)

                container.setBackgroundColor(item.color)

                img.setImageResource(item.drawbleId)
                name.setText(item.textStringId)


                container.setOnClickListener {
                    onItemClickListener.onClick(item.id)
                }
            }
        }
    }
}