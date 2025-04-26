import lab03.src.RoboTerrestre;

public class RoboTerrestreCarga extends RoboTerrestre {
    private int capacidadeCarga; // Capacidade máxima de carga em kg
    private int cargaAtual; // Carga atual em kg

    public RoboTerrestreCarga(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMaxima, int capacidadeCarga) {
        super(nome, direcao, posicaoX, posicaoY, velocidadeMaxima); // Inicializa o robô terrestre com os atributos herdados
        this.capacidadeCarga = capacidadeCarga; // Define a capacidade máxima de carga
        this.cargaAtual = 0; // Inicializa a carga atual como zero
    }

    @Override
    public void mover(int deltaX, int deltaY) {
        // Verifica se a carga atual excede a capacidade máxima antes de mover
        if (cargaAtual > capacidadeCarga) {
            System.out.println("Carga excede a capacidade máxima! Não é possível mover."); // Exibe mensagem de erro
        } else {
            super.mover(deltaX, deltaY); // Realiza o movimento
        }
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga; // Retorna a capacidade máxima de carga
    }

    public void setCapacidadeCarga(int capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga; // Define a capacidade máxima de carga
    }

    public int getCargaAtual() {
        return cargaAtual; // Retorna a carga atual
    }

    public void setCargaAtual(int cargaAtual) {
        this.cargaAtual = cargaAtual; // Define a carga atual
    }
}
