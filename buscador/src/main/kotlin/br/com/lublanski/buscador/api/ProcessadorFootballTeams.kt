package br.com.lublanski.buscador.api

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


@Component
class ProcessadorFootballTeams {
    val uri = "https://jsonmock.hackerrank.com/api/football_teams?nation="
    fun listarTimes(nacao: String) : List<PropriedadesBuscadas> {
        var listaTimes = mutableListOf<PropriedadesBuscadas>()
        val Npaginas = consumirApi(nacao,1)?.total_pages?:0
        var pagina: Long = 1

        while (pagina <= Npaginas){
           listaTimes.addAll(consumirApi(nacao,pagina)?.data?: emptyList())
            pagina++
        }
        return listaTimes.toList()
    }
    private fun consumirApi(nacao: String,pagina: Long): FootballTeamsObj?{
        val restTeamplate = RestTemplate()
        return  restTeamplate.getForObject("${uri}${nacao}&pages=${pagina}",FootballTeamsObj::class.java)
    }
}
