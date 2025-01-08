package br.com.lublanski.buscador

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BuscadorApplication

fun main(args: Array<String>) {
	runApplication<BuscadorApplication>(*args)
}
