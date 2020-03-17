package com.example.buscacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
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

        edt_cep.addTextChangedListener(Mask.custom("#####-###", edt_cep))

        edt_cep.setOnEditorActionListener { v, actionId, event ->

            if ( actionId == EditorInfo.IME_ACTION_DONE ||
                event.action == KeyEvent.ACTION_DOWN &&
                event.keyCode == KeyEvent.KEYCODE_ENTER){
                acessarCep()
                v.hideKeyboard()
                true
            }
            false
        }

        btnMain_buscarCep.setOnClickListener {
            acessarCep()
            it.hideKeyboard()
        }

    }

    fun acessarCep(){

        var CEPService: CEPService =
            retrofit!!.create(CEPService::class.java)

        var call: Call<Cep> = CEPService.buscarCEP(edt_cep.text.toString())


        //metodo enqueue recebe uma classe anonima do tipo callback
        call.enqueue(object : Callback<Cep> { //tratar algum erro
            override fun onFailure(call: Call<Cep>, t: Throwable) {
               Log.e("#retrofit","Erro ao consultar CEP", t)
            }

            override fun onResponse(call: Call<Cep>, response: Response<Cep>) { //pegar resposta
                if(response.isSuccessful) {
                    var cep: Cep? = response.body()
                  //txt_logradouro.text = cep.logradouro

                    txt_resposta.text =
                        "Logradouro: ${cep?.logradouro}\nBairro: ${cep?.bairro}\nCidade: ${cep?.localidade}"

                }
            }
        })
    }
}



