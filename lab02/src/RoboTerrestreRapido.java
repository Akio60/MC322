public class RoboTerrestreRapido extends RoboTerrestre {
    private double multiplicadorVelocidade; // Multiplicador que aumenta a velocidade do robô

    public RoboTerrestreRapido(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMaxima, double multiplicadorVelocidade) {
        super(nome, direcao, posicaoX, posicaoY, velocidadeMaxima); // Inicializa o robô terrestre com os atributos herdados
        this.multiplicadorVelocidade = multiplicadorVelocidade; // Define o multiplicador de velocidade
    }

    @Override
    public void mover(int deltaX, int deltaY) {
        // Ajusta o movimento com base no multiplicador de velocidade
        int novoDeltaX = (int) (deltaX * multiplicadorVelocidade);
        int novoDeltaY = (int) (deltaY * multiplicadorVelocidade);
        if (Math.abs(novoDeltaX) <= velocidadeMaxima && Math.abs(novoDeltaY) <= velocidadeMaxima) {
            super.mover(novoDeltaX, novoDeltaY); // Realiza o movimento ajustado
        } else {
            System.out.println("Movimento excede velocidade máxima ajustada!"); // Exibe mensagem de erro
        }
    }

    public double getMultiplicadorVelocidade() {
        return multiplicadorVelocidade; // Retorna o multiplicador de velocidade
    }

    public void setMultiplicadorVelocidade(double multiplicadorVelocidade) {
        this.multiplicadorVelocidade = multiplicadorVelocidade; // Define o multiplicador de velocidade
    }
}
