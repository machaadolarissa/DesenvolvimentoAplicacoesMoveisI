class MarcianoPremium(private val acoesPersonalizadas: Map<String, MarcianoCustomActions>) : MarcianoWithOperations() {
    override fun reply(frase: String): String {
        if (frase.equals("agir", ignoreCase = true)) {
            return "Sim, tenho algumas ações: ${acoesPersonalizadas.keys.joinToString(", ")}"
        } else if (frase.startsWith("agir ", ignoreCase = true)) {
            val comando = frase.lowercase()
            val acao = acoesPersonalizadas[comando]
            return if (acao != null) {
                "É pra já! ${acao.execute()}"
            } else {
                "Ação não reconhecida"
            }
        }
        return super.reply(frase)
    }
}