package br.com.lublanski.searchengine.api

data class ApiResponseRequestAttributes(
    val name: String,
    val estimated_value_numeric: Long,
    val number_of_league_titles_won: Long
)
