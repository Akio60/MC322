package lab03.src;

public class Obstaculo {
    private final int x;
    private final int y;
    private final int altura;
    private final TipoObstaculo tipo;

    public Obstaculo(int x, int y, int altura, TipoObstaculo tipo) {
        this.x = x;
        this.y = y;
        this.altura = altura;
        this.tipo = tipo;
    }

    public boolean bloqueiaPosicao(int px, int py) {
        return tipo.isBloqueiaPassagem() && px == x && py == y;
    }

    public TipoObstaculo getTipo() {
        return tipo;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getAltura() { return altura; }

    @Override
    public String toString() {
        return String.format("%s em (%d,%d) altura=%d", 
            tipo.getDescricao(), x, y, altura);
    }
}
