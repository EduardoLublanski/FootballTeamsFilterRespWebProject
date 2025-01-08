package br.com.lublanski.buscador.controller

import br.com.lublanski.buscador.servico.ServicoTimes
import br.com.lublanski.buscador.transferenciadados.BuscaDoCliente
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/teams")
class ControllerListaTimes(val servico: ServicoTimes) {
@PostMapping
fun listarTimes(@RequestBody @Validated buscaDoCliente: BuscaDoCliente) : List<String>{
        return servico.listarNomeTimes(buscaDoCliente)
}
}