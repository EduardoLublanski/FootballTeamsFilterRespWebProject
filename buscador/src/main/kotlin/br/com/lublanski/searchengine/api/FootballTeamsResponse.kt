package br.com.lublanski.searchengine.api

import com.fasterxml.jackson.annotation.JsonProperty

data class FootballTeamsResponse(
    val page: Long,
    val total_pages: Long,

    @JsonProperty("data")
    val teamList: List<ApiResponseRequestAttributes>
)