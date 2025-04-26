package LAB03.src;
/**
 * Classe que representa obstáculos no ambiente.
 * Implementa o padrão Composite com a classe Ambiente.
 */
public class Obstaculo {
    private final int posicaoX1;
    private final int posicaoY1;
    private final int posicaoX2;
    private final int posicaoY2;
    private final int altura;
    private final TipoObstaculo tipo;

    public Obstaculo(int x1, int y1, int x2, int y2, TipoObstaculo tipo) {
        this.posicaoX1 = x1;
        this.posicaoY1 = y1;
        this.posicaoX2 = x2;
        this.posicaoY2 = y2;
        this.tipo = tipo;
        this.altura = (tipo == TipoObstaculo.OUTRO) ? -1 : tipo.getAlturaPadrao();
    }

    public boolean bloqueiaPosicao(int x, int y) {
        return tipo.isBloqueiaPassagem()
            && x >= posicaoX1 && x <= posicaoX2
            && y >= posicaoY1 && y <= posicaoY2;
    }

    public TipoObstaculo getTipo() {
        return tipo;
    }

    public int getAltura() {
        return altura;
    }

    // Adicionar getters para acesso seguro às coordenadas
    public int getPosicaoX1() { return posicaoX1; }
    public int getPosicaoY1() { return posicaoY1; }
    public int getPosicaoX2() { return posicaoX2; }
    public int getPosicaoY2() { return posicaoY2; }
}
