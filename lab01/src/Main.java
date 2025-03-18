import ambiente.Ambiente;
import java.util.Scanner;
import robo.Robo;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a largura do ambiente:");
        int largura = scanner.nextInt();
        System.out.println("Digite a altura do ambiente:");
        int altura = scanner.nextInt();

        // Criação do ambiente com as dimensões fornecidas
        Ambiente ambiente = new Ambiente(largura, altura);

        // Criação do robô (mantendo posição inicial fixa para simplicidade)
        Robo robo1 = new Robo("Alpha", 50, 50);
        robo1.exibirPosicao();

        // Leitura dos deslocamentos
        System.out.println("Digite o deslocamento em X:");
        int deltaX = scanner.nextInt();
        System.out.println("Digite o deslocamento em Y:");
        int deltaY = scanner.nextInt();

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

        scanner.close();
    }
}