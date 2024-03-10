
fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 10) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            1 -> {
                val cachorro = Cachorro(lerIdade(), lerCor())
                cachorro.nome = lerNome()
                repositorioAnimal.adicionar(cachorro)
            }
            2 -> {
                val gato = Gato(lerIdade(), lerCor())
                gato.nome = lerNome()
                repositorioAnimal.adicionar(gato)
            }
            3 -> {
                val passaro = Passaro(lerIdade(), lerCor())
                passaro.nome = lerNome()
                repositorioAnimal.adicionar(passaro)
            }
            4 -> {
                val pessoa = Homem(lerIdade(), lerCor())
                pessoa.nome = lerNome()
                repositorioAnimal.adicionar(pessoa)
            }
            5 -> {
                repositorioAnimal.listar()
            }
            6 -> {
                println("Digite a cor dos animais a serem listados:")
                val cor = lerCor()
                repositorioAnimal.listarPorCor(cor)
            }
            7 -> {
                println("Digite a idade dos animais a serem listados:")
                val idade = lerIdade()
                repositorioAnimal.listarPorIdade(idade)
            }
            8 -> {
                println("Digite o nome do animal a ser buscado:")
                val nome = readLine()
                val animal = repositorioAnimal.buscarPorNome(nome)
                if (animal != null) {
                    println("Animal encontrado: ${animal.nome}")
                } else {
                    println("Animal não encontrado.")
                }
            }
            9 -> {
                println("Digite o nome do animal a ser removido:")
                val nomeRemover = readLine()
                var animalRemovido = false
                for (animal in repositorioAnimal.animais.toList()) {
                    if (animal.nome == nomeRemover) {
                        repositorioAnimal.remover(animal)
                        println("$nomeRemover foi removido da lista.")
                        animalRemovido = true
                        break
                    }
                }
                if (!animalRemovido) {
                    println("Animal não encontrado na lista.")
                }

            }
            10 -> {
                println("Saindo...")

            }
            else -> {
                println("Opção inválida, por favor escolha uma opção válida.")
            }
        }
    }
}

enum class Cor {
    MARROM,
    CINZA,
    AMARELO
}

abstract class Animal(var idade: Int, var cor: Cor) {
    abstract var nome: String

    abstract fun emitirSom()

    open fun idadeEmAnosHumanos(): Int {
        return idade * 7
    }
}

class Cachorro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Au au")
    }
}

class Gato(idade: Int, cor: Cor) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Piu piu")
    }
}

class Homem(idade: Int, cor: Cor) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Oi")
    }
    override fun idadeEmAnosHumanos(): Int {
        return idade
    }
}

fun menu() {
    println("1 - Adicionar Cachorro")
    println("2 - Adicionar Gato")
    println("3 - Adicionar Pássaro")
    println("4 - Adicionar Pessoa")
    println("5 - Listar Animais")
    println("6 - Listar por Cor")
    println("7 - Listar por Idade")
    println("8 - Buscar por Nome")
    println("9 - Remover Animal")
    println("10 - Sair")

}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        println("Lista de animais:")
        animais.forEach { println("Nome: ${it.nome}, Idade: ${it.idade}, Cor: ${it.cor}, Idade humana: ${it.idadeEmAnosHumanos()}") }
    }

    fun listarPorCor(cor: Cor) {
        val animaisFiltrados = animais.filter { it.cor == cor }
        if (animaisFiltrados.isNotEmpty()) {
            println("Animais da cor $cor:")
            animaisFiltrados.forEach { println("Nome: ${it.nome}, Idade: ${it.idade}, Cor: ${it.cor}, Idade humana: ${it.idadeEmAnosHumanos()}") }
        } else {
            println("Nenhum animal encontrado com a cor especificada.")
        }
    }

    fun listarPorIdade(idade: Int) {
        val animaisFiltrados = animais.filter { it.idade == idade }
        if (animaisFiltrados.isNotEmpty()) {
            println("Animais com $idade anos de idade:")
            animaisFiltrados.forEach { println("Nome: ${it.nome}, Idade: ${it.idade}, Cor: ${it.cor}, Idade humana: ${it.idadeEmAnosHumanos()}") }
        } else {
            println("Nenhum animal encontrado com a idade especificada.")
        }
    }

    fun buscarPorNome(nome: String?): Animal? {
        return animais.find { it.nome == nome }
    }

    fun remover(animal: Animal) {
        animais.remove(animal)
    }
}

fun lerNome(): String {
    println("Digite o nome do animal:")
    return readLine() ?: ""
}

fun lerIdade(): Int {
    println("Digite a idade do animal:")
    return readlnOrNull()?.toInt() ?: 0
}

fun lerCor(): Cor {
    println("Digite a cor do animal (MARROM, CINZA, AMARELO):")
    val corStr = readLine()?.uppercase() ?: ""
    return Cor.valueOf(corStr)
}

