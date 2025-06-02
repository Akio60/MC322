/**
 * Enumeração que define os tipos possíveis de obstáculos.
 * Cada tipo tem suas próprias características.
 * Implementa o padrão Value Object para propriedades imutáveis.
 */
public enum TipoObstaculo {
    // Definição dos tipos com suas características
    BASE_ALIADA(true, "Base Aliada"),    // Base amiga
    ARMADILHA(true, "Armadilha"),        // Perigo para navegação
    ARVORE(true, "Árvore"),              // Obstáculo natural
    ROCHA(true, "Rocha"),                // Obstáculo natural
    BASE_INIMIGA(true, "Base Inimiga");  // Base hostil

    private final boolean bloqueiaPassagem;
    private final String descricao;

    TipoObstaculo(boolean bloqueiaPassagem, String descricao) {
        this.bloqueiaPassagem = bloqueiaPassagem;
        this.descricao = descricao;
    }

    public boolean isBloqueiaPassagem() {
        return bloqueiaPassagem;
    }

    public String getDescricao() {
        return descricao;
    }
}