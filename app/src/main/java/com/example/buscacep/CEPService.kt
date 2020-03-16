package com.example.buscacep


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface CEPService {
    @GET ("{cep}/json/") // informando a URL. Na lacuna {} entrará o CEP digitado pelo usuario
    fun buscarCEP(@Path("cep")cep: String): Call<Cep>


}