public class Main {
    public static void main(String[] args) {
        // Criação de um objeto Ambiente com dimensões definidas
        Ambiente ambiente = new Ambiente(100, 100);

        // Criação de um objeto Robo com posição inicial
        Robo robo1 = new Robo("Alpha", 50, 50);
        robo1.exibirPosicao();

        // Definição dos deslocamentos desejados
        int deltaX = 10;
        int deltaY = -20;
        int novoX = robo1.getPosicaoX() + deltaX;
        int novoY = robo1.getPosicaoY() + deltaY;

        // Verificação se a nova posição está dentro dos limites do ambiente
        if (ambiente.dentroDosLimites(novoX, novoY)) {
            robo1.mover(deltaX, deltaY);
            System.out.println("Movimentando o robo para (" + novoX + ", " + novoY + ")");
        } else {
            System.out.println("Nova posicao (" + novoX + ", " + novoY + ") fora dos limites do ambiente.");
        }

        // Exibição da posição final do robô
        robo1.exibirPosicao();
    }
}
