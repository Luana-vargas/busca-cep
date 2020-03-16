package com.example.buscacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private var retrofit: Retrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = RetrofitConfig.getInstance().makeRetrofit()

        btnMain_buscarCep.setOnClickListener {
            acessarCep()
        }

    }

    fun acessarCep(){

        var CEPService: CEPService =
            retrofit!!.create(CEPService::class.java)

        var call: Call<Cep> = CEPService.buscarCEP(etMain_cep.text.toString())


        //metodo enqueue recebe uma classe anonima do tipo callback
        call.enqueue(object : Callback<Cep> { //tratar algum erro
            override fun onFailure(call: Call<Cep>, t: Throwable) {
               Log.e("#retrofit","Erro ao consultar CEP", t)
            }

            override fun onResponse(call: Call<Cep>, response: Response<Cep>) { //pegar resposta
                if(response.isSuccessful) {
                    var cep: Cep? = response.body()
                  //txt_logradouro.text = cep.logradouro

                    etMain_resposta.text =
                        "${cep?.logradouro} / ${cep?.bairro} / ${cep?.localidade}"

                }
            }
        })
    }
}



