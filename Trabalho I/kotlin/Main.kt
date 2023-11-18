fun main() {
    println("Bem-vindo ao robô Marciano Premium!")
    println("Digite 'FIM' para encerrar.")

    val acoesPersonalizadas = mapOf(
        "agir contar" to object : MarcianoAcaoPersonalizada {
            override fun executar(): String {
                val contador = StringBuilder("Conto até 10: ")
                for (i in 1..10) {
                    contador.append(i)
                    if (i < 10) {
                        contador.append(", ")
                    }
                }
                return contador.toString()
            }
        },
        "agir alfabeto" to object : MarcianoAcaoPersonalizada {
            override fun executar(): String {
                return "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
            }
        },
        "agir fato" to object : MarcianoAcaoPersonalizada {
            override fun executar(): String {
                return "Aqui está um fato interessante do Kotlin: Kotlin é uma linguagem de programação oficialmente " +
                        "suportada para o desenvolvimento Android."
            }
        }
    )

    val robopremium = MarcianoPremium(acoesPersonalizadas)

    while (true) {
        print("Você: ")
        val entrada = readLine() ?: ""
        if (entrada.equals("FIM", ignoreCase = true)) {
            break
        }

        val resposta = robopremium.responda(entrada)
        println("Robô: $resposta")
    }
}