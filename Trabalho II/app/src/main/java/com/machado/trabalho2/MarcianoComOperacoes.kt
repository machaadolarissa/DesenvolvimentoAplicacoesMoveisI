open class MarcianoComOperacoes : Marciano() {
    override fun responda(frase: String): String {
        val regex = Regex("(some|subtraia|multiplique|divida) (\\d+) (\\d+)")
        val matchResult = regex.find(frase)

        if (matchResult != null) {
            val (operacao, operando1, operando2) = matchResult.destructured
            return when (operacao) {
                "some" -> "Essa eu sei. Resultado: ${operando1.toInt() + operando2.toInt()}"
                "subtraia" -> "Essa eu sei. Resultado: ${operando1.toInt() - operando2.toInt()}"
                "multiplique" -> "Essa eu sei. Resultado: ${operando1.toInt() * operando2.toInt()}"
                "divida" -> {
                    val resultado = (operando1.toDouble() / operando2.toDouble())
                    if (resultado % 1.0 == 0.0) {
                        "Essa eu sei. Resultado: ${resultado.toInt()}"
                    } else {
                        "Essa eu sei. Resultado: ${"%.1f".format(resultado)}"
                    }
                }
                else -> responda(frase)
            }
        }
        return super.responda(frase)
    }
}