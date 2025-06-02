import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializar ambiente
        Ambiente ambiente = new Ambiente(10, 10, 3); // Ex: 10x10 com 3 níveis
        CentralComunicacao central = CentralComunicacao.getInstancia();

        // Criar robôs
        RoboTerrestre robo1 = new RoboTerrestre("R1", 1, 1);
        RoboAereo robo2 = new RoboAereo("R2", 2, 2, 1);
        RoboAereo robo3 = new RoboAereo("R3", 5, 5, 2);

        // Ligar e configurar comunicação se necessário
        robo1.ligar();
        robo2.ligar();
        robo3.ligar();

        robo2.configurarComunicacao(central);
        robo3.configurarComunicacao(central);

        // Adicionar ao ambiente
        try {
            ambiente.adicionarEntidade(robo1);
            ambiente.adicionarEntidade(robo2);
            ambiente.adicionarEntidade(robo3);
        } catch (Exception e) {
            System.err.println("Erro ao adicionar robôs: " + e.getMessage());
        }

        // Iniciar menu
        Scanner scanner = new Scanner(System.in);
        MenuInterativo.menuPrincipal(scanner, ambiente, central);
        scanner.close();
    }
}