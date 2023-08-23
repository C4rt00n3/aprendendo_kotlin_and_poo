import java.time.LocalDateTime

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }
enum class Sexo { Famale, Masculine }

class Usuario (
    val nome: String,
    val dataDeNacimento: LocalDateTime,
    val sexo: Sexo,
) {}

data class ConteudoEducacional(
    val nome: String,
    val duracao: Int,
    val materia: String,
    val professor: String,
    var nota: Int,
    val level: Nivel,

) {}

data class Formacao(
    val nome: String,
    var conteudos: List<ConteudoEducacional>,
    val inscritos: MutableList<Usuario>
){
    fun matricular(usuario: Usuario): String {
        inscritos.add(usuario)
        return "Cadastrado com sucesso!"
    }

    fun calcularMediaAvaliacoes(): Double {
        val somaNotas: Int = conteudos.sumBy { it.nota }
        val quantidadeConteudos: Int = conteudos.size

        return if (quantidadeConteudos > 0) {
            somaNotas.toDouble() / quantidadeConteudos
        } else {
            0.0
        }
    }
}

fun main() {
    val usuario1 = Usuario("Alice", LocalDateTime.of(1995, 5, 15, 0, 0), Sexo.Famale)
    val usuario2 = Usuario("Bob", LocalDateTime.of(1990, 8, 20, 0, 0), Sexo.Masculine)

    val conteudo1 = ConteudoEducacional (
        "Introdução à Programação",
        90,
        "Programação",
        "Prof. Smith",
        6,
        Nivel.BASICO
    )
    val conteudo2 = ConteudoEducacional(
        "Algoritmos Avançados",
        120,
        "Algoritmos",
        "Prof. Johnson",
        8,
        Nivel.DIFICIL)

    val formacao = Formacao("Ciência da Computação", listOf(conteudo1, conteudo2), mutableListOf())

    formacao.matricular(usuario1)
    formacao.matricular(usuario2)

    println("Inscritos na formação ${formacao.nome}: ${formacao.inscritos.size}")

    // Simulando avaliações
    conteudo1.nota = 4
    conteudo2.nota = 5

    val mediaAvaliacoesFormacao = formacao.calcularMediaAvaliacoes()
    println("Média de avaliações da formação: $mediaAvaliacoesFormacao")
}
