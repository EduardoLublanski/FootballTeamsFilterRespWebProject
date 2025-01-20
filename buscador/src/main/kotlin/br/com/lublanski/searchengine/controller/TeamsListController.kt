package br.com.lublanski.searchengine.controller

import br.com.lublanski.searchengine.service.SearchEngine
import br.com.lublanski.searchengine.datamap.Team
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/teams")
class TeamsListController(val searchEngine: SearchEngine) {

        @PostMapping
        fun getFilteredTeams(@RequestBody @Validated clientFilters: Team) : List<String>{
                return searchEngine.getFilteredNames(clientFilters)
        }
}