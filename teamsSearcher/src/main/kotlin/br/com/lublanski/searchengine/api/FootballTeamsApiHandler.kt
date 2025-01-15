package br.com.lublanski.searchengine.api

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


@Component
class FootballTeamsApiHandler: PaginatedApiHandler<ApiResponseRequestAttributes,FootballTeamsResponse> {
    
    override val uri = "https://jsonmock.hackerrank.com/api/football_teams?nation="
    override val firstPage: Long = 1;
    override val noPages: Long = 0

    override fun getAllPages(nation: String) : List<ApiResponseRequestAttributes> {

        var teams = mutableListOf<ApiResponseRequestAttributes>()
        val teamPagesAmount = getResponse(nation,firstPage)?.total_pages?:noPages

        var currentApiPage = firstPage
        while (currentApiPage <= teamPagesAmount){
           teams.addAll(getResponse(nation,currentApiPage)?.teamList?: emptyList())
            currentApiPage++
        }

        teams = teams.distinctBy { it.name }.toMutableList()

        return teams.toList()
    }

    override fun getResponse(nacao: String, pagina: Long): FootballTeamsResponse?{
        val restTeamplate = RestTemplate()
        return  restTeamplate.getForObject("${uri}${nacao}&pages=${pagina}",FootballTeamsResponse::class.java)
    }
}
