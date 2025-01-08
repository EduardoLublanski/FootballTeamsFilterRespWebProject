package br.com.lublanski.buscador.transferenciadados

import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class BuscaDoCliente(
    @field:NotEmpty(message = "a nação não pode ser nula")
    @field:Size(min = 3, max = 45)
    val nation: String,
    @field:Min(0)
    val minValuation: Long,
    @field:Min(0)
    val minTitlesWon: Int
)


