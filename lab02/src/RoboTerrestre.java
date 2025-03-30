public class RoboTerrestre extends Robo {
    int velocidadeMaxima;

    public RoboTerrestre(String nome,
                         String direcao, 
                         int posicaoX, 
                         int posicaoY, 
                         int velocidadeMaxima) {
        super(nome, direcao, posicaoX, posicaoY);
        this.velocidadeMaxima = velocidadeMaxima;
    }

    @Override
    public void mover(int deltaX, int deltaY) {
        if (Math.abs(deltaX) <= velocidadeMaxima && Math.abs(deltaY) <= velocidadeMaxima) {
            super.mover(deltaX, deltaY);
        } else {
            System.out.println("Movimento excede velocidade máxima!");
        }
    }

    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }
}
