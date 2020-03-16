package com.example.buscacep

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    //Criando função que ficará responsável por inicializar o Retrofit

    var retrofit: Retrofit? = null
    companion object {
        private var instance: RetrofitConfig? = null

        @Synchronized // esta anotação protege o método contra acesso concorrente de múltiplas threads
        fun getInstance(): RetrofitConfig {
            if (instance == null) {
                instance = RetrofitConfig()
            }
            return instance!!
        }
    }

    fun makeRetrofit(): Retrofit? {
        return Retrofit.Builder() // classe resp. por construir objeto do tipo Retrofit
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create()) // configurado o conversor
            .build() // construindo objeto da classe Retrofit
    }




}