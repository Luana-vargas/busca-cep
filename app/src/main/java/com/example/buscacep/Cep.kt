package com.example.buscacep

import com.google.gson.annotations.SerializedName

data class Cep (
    var cep: String,
   // @SerializedName("logradouro") para alterar nome
    var logradouro: String,
    var complemento: String,
    var bairro: String,
    var localidade: String,
    var uf: String
)