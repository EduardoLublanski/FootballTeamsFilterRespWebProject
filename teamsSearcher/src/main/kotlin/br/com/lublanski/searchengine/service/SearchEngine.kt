package br.com.lublanski.searchengine.service


import br.com.lublanski.searchengine.api.FootballTeamsApiHandler
import br.com.lublanski.searchengine.api.ApiResponseRequestAttributes
import br.com.lublanski.searchengine.datamap.Team
import org.springframework.stereotype.Service

@Service
class SearchEngine(val footballTeamsApiHandler:FootballTeamsApiHandler) {

    fun getFilteredNames(team: Team): List<String> {

        val nation = team.nation
        val teamsList = getTeamsList(nation)
        val minTitlesWon = team.minTitlesWon
        val minValue = team.minValuation

        val filteredList = filterByTitlesWonAndValuation(teamsList, minValue, minTitlesWon)

        return sortByValueThenName(filteredList).map { it.name }

        }

    private fun getTeamsList(nation: String): List<ApiResponseRequestAttributes>{
        val teamList = footballTeamsApiHandler.getAllPages(nation)

        if (teamList.isEmpty()){
            throw NoSuchElementException("no teams found for specific nation '${nation}'")
        }

        return  teamList
    }

    private fun filterByTitlesWonAndValuation(
        teams: List<ApiResponseRequestAttributes>,
        minValue: Long, minTitlesWon: Int
        ): List<ApiResponseRequestAttributes>{

        return teams.filter {
            it.estimated_value_numeric >= minValue && it.number_of_league_titles_won >= minTitlesWon
        }

    }

    private fun sortByValueThenName(teams: List<ApiResponseRequestAttributes>): List<ApiResponseRequestAttributes>{

        return teams.sortedWith(compareByDescending<ApiResponseRequestAttributes> { it.estimated_value_numeric }
                .thenBy { it.name })

    }

}

