package br.com.lublanski.buscador.transferenciadados

import java.time.LocalDateTime

data class ExceptionFootballTeams(
    val dataHora: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val erro: String,
    val mensagem: String,
    val caminho: String
)


