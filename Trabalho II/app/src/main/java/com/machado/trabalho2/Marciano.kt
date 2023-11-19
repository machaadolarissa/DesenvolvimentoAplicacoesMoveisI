open class Marciano {
    open fun reply(frase: String): String {
        val regexMaiusculas = Regex("[A-Z]")
        return if (frase.isEmpty()) {
            "Não me incomode"
        } else if (frase.endsWith("?") && !frase.contains(regexMaiusculas)) {
            "Certamente"
        } else if (frase.contains(regexMaiusculas) && !frase.endsWith("?") && !frase.contains(
                "eu",
                ignoreCase = true
            )) {
            "Opa! Calma aí!"
        } else if (frase.contains(regexMaiusculas) && frase.endsWith("?")) {
            "Relaxa, eu sei o que estou fazendo!"
        } else if (frase.contains("eu", ignoreCase = true)) {
            return "A responsabilidade é sua"
        } else {
            return "Tudo bem, como quiser"
        }
    }
}