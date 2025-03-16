package ambiente;

public class Ambiente {
    // Atributos do ambiente
    private int largura;
    private int altura;

    // Construtor para inicializar os atributos do ambiente
    public Ambiente(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    // Método que verifica se uma determinada posição (x, y) está dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y) {
        return (x >= 0 && x <= largura) && (y >= 0 && y <= altura);
    }
    
    public int getLargura() {
        return largura;
    }
    
    public int getAltura() {
        return altura;
    }
}

