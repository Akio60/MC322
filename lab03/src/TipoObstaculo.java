package lab03.src;

public enum TipoObstaculo {
    BASE_ALIADA(true, "Base Aliada"),
    ARMADILHA(true, "Armadilha"),
    ARVORE(true, "Árvore"),
    ROCHA(true, "Rocha"),
    BASE_INIMIGA(true, "Base Inimiga");

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