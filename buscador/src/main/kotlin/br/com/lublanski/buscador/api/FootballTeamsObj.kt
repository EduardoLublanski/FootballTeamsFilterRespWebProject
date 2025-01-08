package br.com.lublanski.buscador.api

data class FootballTeamsObj(
    val page: Long,
    val total_pages: Long,
    val data: List<PropriedadesBuscadas>
)