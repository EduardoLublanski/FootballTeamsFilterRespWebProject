package br.com.lublanski.buscador.servico


import br.com.lublanski.buscador.api.ProcessadorFootballTeams
import br.com.lublanski.buscador.api.PropriedadesBuscadas
import br.com.lublanski.buscador.transferenciadados.BuscaDoCliente
import org.springframework.stereotype.Service

@Service
class ServicoTimes(val componente: ProcessadorFootballTeams) {

    fun listarNomeTimes(buscaDoCliente: BuscaDoCliente): List<String> {
        val listaDadosTimes = componente.listarTimes(buscaDoCliente.nation)
        if (listaDadosTimes.isEmpty()) throw NoSuchElementException("Nação sem times cadastrados")
        var listaTimesFiltrados = listaDadosTimes.filter {
            it.estimated_value_numeric>=buscaDoCliente.minValuation &&
                    it.number_of_league_titles_won>=buscaDoCliente.minTitlesWon
        }
        listaTimesFiltrados = listaTimesFiltrados.distinctBy { it.name }
        return listaTimesFiltrados
            .sortedWith(compareByDescending<PropriedadesBuscadas> { it.estimated_value_numeric }
                .thenBy { it.name })
            .map { it.name }


        }
}

