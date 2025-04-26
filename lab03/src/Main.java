package lab03.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String SEPARADOR = "==============================================";
    
    private static void mostrarTitulo(String titulo) {
        System.out.println("\n" + SEPARADOR);
        System.out.println(titulo);
        System.out.println(SEPARADOR);
    }

    private static void adicionarSensores(Scanner sc, Robo robo) {
        while (true) {
            mostrarTitulo("ADICIONAR SENSORES AO ROBÔ " + robo.getNome());
            System.out.println("  1. Sensor de Terreno");
            System.out.println("    > Detecta e analisa obstáculos próximos");
            System.out.println("  2. Sensor de Navegação");
            System.out.println("    > Auxilia na navegação e detecção de altura");
            System.out.println("  3. Sensor Tático");
            System.out.println("    > Localiza bases e outros robôs");
            System.out.println("  0. Finalizar adição de sensores");
            
            System.out.print("\nEscolha o sensor (0-3): ");
            int opcao = sc.nextInt();
            
            if (opcao == 0) break;
            
            System.out.print("Digite o raio de alcance do sensor: ");
            double raio = sc.nextDouble();
            
            switch (opcao) {
                case 1:
                    robo.adicionarSensor(new SensorTerreno(robo, raio));
                    break;
                case 2:
                    robo.adicionarSensor(new SensorNavegacao(robo, raio));
                    break;
                case 3:
                    robo.adicionarSensor(new SensorTatico(robo, raio));
                    break;
                default:
                    System.out.println("Opção inválida!");
                    continue;
            }
            System.out.println(">>> Sensor adicionado com sucesso! <<<");
        }
    }

    private static void inicializarRobos(Scanner sc, Ambiente ambiente) {
        mostrarTitulo("INICIALIZAÇÃO DE ROBÔ");
        System.out.println("Tipo (1-Terrestre, 2-Aéreo):");
        int tipo = sc.nextInt();
        System.out.print("Nome: ");
        String nome = sc.next();
        System.out.print("Posição X Y: ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        
        try {
            if (tipo == 1) {
                RoboTerrestre r = new RoboTerrestre(nome, x, y);
                adicionarSensores(sc, r);
                ambiente.adicionarRobo(r);
            } else if (tipo == 2) {
                System.out.print("Altitude inicial: ");
                int alt = sc.nextInt();
                RoboAereo r = new RoboAereo(nome, x, y, alt);
                adicionarSensores(sc, r);
                ambiente.adicionarRobo(r);
            }
            System.out.println(">>> Robô adicionado com sucesso! <<<");
        } catch (IllegalArgumentException e) {
            System.out.println(">>> ERRO: " + e.getMessage() + " <<<");
        }
    }

    private static void exibirObstaculos(ArrayList<Obstaculo> obstaculos) {
        if (obstaculos.isEmpty()) {
            System.out.println("\nNenhum obstáculo cadastrado.");
            return;
        }
        mostrarTitulo("LISTA DE OBSTÁCULOS");
        for (int i = 0; i < obstaculos.size(); i++) {
            System.out.printf("  %d. %s\n", i+1, obstaculos.get(i).toString());
        }
    }

    private static TipoObstaculo selecionarTipoObstaculo(Scanner sc) {
        mostrarTitulo("TIPOS DE OBSTÁCULOS DISPONÍVEIS");
        TipoObstaculo[] tipos = TipoObstaculo.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.printf("  %d. %s\n", i+1, tipos[i].getDescricao());
        }
        System.out.print("\nEscolha o tipo (1-" + tipos.length + "): ");
        int escolha = sc.nextInt();
        return tipos[escolha - 1];
    }

    private static void removerRobo(Scanner sc, Ambiente ambiente) {
        ArrayList<Robo> robos = ambiente.getRobos();
        if (robos.isEmpty()) {
            System.out.println("\nNenhum robô cadastrado.");
            return;
        }
        
        mostrarTitulo("LISTA DE ROBÔS");
        for (int i = 0; i < robos.size(); i++) {
            Robo r = robos.get(i);
            System.out.printf("  %d. %s em (%d,%d) altitude=%d\n", 
                i+1, r.getNome(), r.getX(), r.getY(), r.getAltitude());
        }
        
        System.out.print("\nDigite o número do robô a remover: ");
        int idx = sc.nextInt() - 1;
        if (idx >= 0 && idx < robos.size()) {
            Robo removido = robos.remove(idx);
            System.out.println("\n>>> Robô '" + removido.getNome() + "' removido com sucesso! <<<");
        } else {
            System.out.println("\n>>> Índice inválido! <<<");
        }
    }
    private static void usarSensores(Scanner sc, Ambiente ambiente) {
        ArrayList<Robo> robos = ambiente.getRobos();
        if (robos.isEmpty()) {
            System.out.println("\nNenhum robô cadastrado.");
            return;
        }
        
        mostrarTitulo("SELECIONAR ROBÔ PARA USAR SENSORES");
        for (int i = 0; i < robos.size(); i++) {
            Robo r = robos.get(i);
            System.out.printf("  %d. %s em (%d,%d) altitude=%d\n", 
                i+1, r.getNome(), r.getX(), r.getY(), r.getAltitude());
        }
        
        System.out.print("\nEscolha o robô (1-" + robos.size() + "): ");
        int idx = sc.nextInt() - 1;
        
        if (idx >= 0 && idx < robos.size()) {
            Robo robo = robos.get(idx);
            mostrarTitulo("LEITURAS DOS SENSORES - " + robo.getNome());
            robo.usarSensores(ambiente);
        } else {
            System.out.println("\n>>> Índice inválido! <<<");
        }
    }

    public static void main(String[] args) {
        Ambiente ambiente = new Ambiente(100, 100);
        Scanner sc = new Scanner(System.in);

        mostrarTitulo("BEM-VINDO AO SIMULADOR DE ROBÔS");

        while (true) {
            mostrarTitulo("MENU PRINCIPAL");
            System.out.println("  1. Visualizar status dos robôs");
            System.out.println("  2. Gerenciar robôs");
            System.out.println("    > Adicionar, remover ou mover robôs");
            System.out.println("  3. Gerenciar obstáculos");
            System.out.println("    > Listar, adicionar ou remover obstáculos");
            System.out.println("  4. Usar sensores");
            System.out.println("  0. Sair");
            System.out.print("\nEscolha uma opção: ");
            
            int opc = sc.nextInt();
            if (opc == 0) break;

            try {
                switch (opc) {
                    case 1:
                        mostrarTitulo("STATUS DOS ROBÔS");
                        for (Robo r : ambiente.getRobos()) {
                            System.out.printf("  %s em (%d,%d) altitude=%d\n", 
                                r.getNome(), r.getX(), r.getY(), r.getAltitude());
                        }
                        break;
                    case 2:
                        gerenciarRobos(sc, ambiente);
                        break;
                    case 3:
                        gerenciarObstaculos(sc, ambiente);
                        break;
                    case 4:
                        usarSensores(sc, ambiente);
                        break;
                    default:
                        System.out.println("\n>>> Opção inválida! <<<");
                }
                System.out.println("\nPressione ENTER para continuar...");
                sc.nextLine(); // limpa buffer
                sc.nextLine(); // espera ENTER
            } catch (Exception e) {
                System.out.println("\n>>> ERRO: " + e.getMessage() + " <<<");
                sc.nextLine(); // limpa buffer
                System.out.println("\nPressione ENTER para continuar...");
                sc.nextLine();
            }
        }
        mostrarTitulo("SIMULAÇÃO ENCERRADA");
        sc.close();
    }

    private static void gerenciarRobos(Scanner sc, Ambiente ambiente) {
        while (true) {
            mostrarTitulo("GERENCIAR ROBÔS");
            System.out.println("  1. Adicionar novo robô");
            System.out.println("  2. Remover robô");
            System.out.println("  3. Mover robô");
            System.out.println("  0. Voltar ao menu principal");
            System.out.print("\nEscolha uma opção: ");
            
            int opc = sc.nextInt();
            if (opc == 0) break;

            try {
                switch (opc) {
                    case 1:
                        inicializarRobos(sc, ambiente);
                        break;
                    case 2:
                        removerRobo(sc, ambiente);
                        break;
                    case 3:
                        System.out.print("Nome do robo: ");
                        String nome = sc.next();
                        System.out.print("dx dy dh: ");
                        int dx = sc.nextInt(), dy = sc.nextInt(), dh = sc.nextInt();
                        for (Robo r : ambiente.getRobos()) {
                            if (r.getNome().equalsIgnoreCase(nome)) {
                                r.mover(dx, dy, dh);
                            }
                        }
                        break;
                    default:
                        System.out.println("\n>>> Opção inválida! <<<");
                }
            } catch (Exception e) {
                System.out.println("\n>>> ERRO: " + e.getMessage() + " <<<");
                sc.nextLine();
            }
        }
    }

    private static void gerenciarObstaculos(Scanner sc, Ambiente ambiente) {
        while (true) {
            mostrarTitulo("GERENCIAR OBSTÁCULOS");
            System.out.println("  1. Listar obstáculos");
            System.out.println("  2. Adicionar obstáculo");
            System.out.println("  3. Remover obstáculo");
            System.out.println("  0. Voltar ao menu principal");
            System.out.print("\nEscolha uma opção: ");
            
            int opc = sc.nextInt();
            if (opc == 0) break;

            try {
                switch (opc) {
                    case 1:
                        exibirObstaculos(ambiente.getObstaculos());
                        break;
                    case 2:
                        mostrarTitulo("ADICIONAR OBSTÁCULO");
                        TipoObstaculo tipoSelecionado = selecionarTipoObstaculo(sc);
                        System.out.print("\nPosição (X Y): ");
                        int x = sc.nextInt(), y = sc.nextInt();
                        System.out.print("Altura: ");
                        int altura = sc.nextInt();
                        
                        ambiente.adicionarObstaculo(new Obstaculo(x, y, altura, tipoSelecionado));
                        System.out.println("\n>>> Obstáculo adicionado com sucesso! <<<");
                        Thread.sleep(1500);
                        break;
                    case 3:
                        mostrarTitulo("REMOVER OBSTÁCULO");
                        ArrayList<Obstaculo> obstaculos = ambiente.getObstaculos();
                        exibirObstaculos(obstaculos);
                        if (!obstaculos.isEmpty()) {
                            System.out.print("Digite o número do obstáculo a remover: ");
                            int idx = sc.nextInt() - 1;
                            if (idx >= 0 && idx < obstaculos.size()) {
                                obstaculos.remove(idx);
                                System.out.println("Obstáculo removido!");
                            } else {
                                System.out.println("Índice inválido!");
                            }
                        }
                        break;
                    default:
                        System.out.println("\n>>> Opção inválida! <<<");
                }
            } catch (Exception e) {
                System.out.println("\n>>> ERRO: " + e.getMessage() + " <<<");
                sc.nextLine();
            }
        }
    }
}