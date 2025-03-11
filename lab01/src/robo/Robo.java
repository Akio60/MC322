package robo;

public class Robo {
    // Atributos
    private String nome;
    private int posicaoX;
    private int posicaoY;

    // Construtor para inicializar os atributos
    public Robo(String nome, int posicaoX, int posicaoY) {
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    // Método para mover o robô (atualiza a posição)
    public void mover(int deltaX, int deltaY) {
        posicaoX += deltaX;
        posicaoY += deltaY;
    }

    // Método para exibir a posição atual do robô
    public void exibirPosicao() {
        System.out.println("Robo " + nome + " posicao: (" + posicaoX + ", " + posicaoY + ")");
    }

    // Getters para as posições, se necessário para verificações externas
    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    } 
}
