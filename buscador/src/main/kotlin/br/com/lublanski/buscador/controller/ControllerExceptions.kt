package br.com.lublanski.buscador.controller

import br.com.lublanski.buscador.transferenciadados.ExceptionFootballTeams
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.util.UriBuilder
import java.time.LocalDateTime

@RestControllerAdvice
class ControllerExceptions {
@ExceptionHandler(NoSuchElementException::class)
@ResponseStatus(HttpStatus.NOT_FOUND)
fun noSuchElementException(
    exception: NoSuchElementException,
    uri: HttpServletRequest
): ExceptionFootballTeams {
return ExceptionFootballTeams(
    dataHora = LocalDateTime.now(),
    erro = HttpStatus.NOT_FOUND.name,
    status = HttpStatus.NOT_FOUND.value(),
    mensagem = exception.message.toString(),
    caminho = uri.servletPath
)
}
   @ExceptionHandler(MethodArgumentNotValidException::class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   fun methodArgumentNotValidException(
       exception: MethodArgumentNotValidException,
       uri: HttpServletRequest) : ExceptionFootballTeams{
       val listaErros = HashMap<String,String?>()
       exception.bindingResult.fieldErrors.forEach {
           erro -> listaErros.put(erro.field,erro.defaultMessage)
       }
       return ExceptionFootballTeams(
           dataHora = LocalDateTime.now(),
           erro = HttpStatus.BAD_REQUEST.name,
           status = HttpStatus.BAD_REQUEST.value(),
           mensagem = listaErros.toString(),
           caminho = uri.servletPath
       )
   }
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exception(
        exception: Exception,
        uri: HttpServletRequest
    ): ExceptionFootballTeams{
        return ExceptionFootballTeams(
            dataHora = LocalDateTime.now(),
            erro = HttpStatus.BAD_REQUEST.name,
            status = HttpStatus.BAD_REQUEST.value(),
            mensagem = exception.message.toString(),
            caminho = uri.servletPath
        )
    }
}