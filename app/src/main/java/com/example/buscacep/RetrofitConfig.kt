package com.example.buscacep

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    //Criando função que ficará responsável por inicializar o Retrofit
    fun init(){
        Retrofit.Builder() // classe resp. por construir objeto do tipo Retrofit
            .baseUrl("http://192.168.0.23:8080/")
            .addConverterFactory(GsonConverterFactory.create()) // configurado o conversor
    }
}