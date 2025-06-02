import java.util.ArrayList;
import java.util.Scanner;

public class MenuInterativo {

    public static void menuPrincipal(Scanner sc, Ambiente ambiente, CentralComunicacao central) {
        boolean executando = true;
        Entidade roboSelecionado = null;

        while (executando) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Listar robôs por tipo");
            System.out.println("2. Listar robôs por estado (ligado/desligado)");
            System.out.println("3. Selecionar robô para interagir");
            System.out.println("4. Visualizar status do robô selecionado");
            System.out.println("5. Visualizar mapa do ambiente");
            System.out.println("6. Executar ações do robô selecionado");
            System.out.println("7. Mover robô selecionado");
            System.out.println("8. Ativar/Desligar robô");
            System.out.println("9. Ver mensagens trocadas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    listarPorTipo(ambiente.getEntidades());
                    break;
                case "2":
                    listarPorEstado(ambiente.getEntidades());
                    break;
                case "3":
                    roboSelecionado = selecionarRobo(ambiente.getEntidades(), sc);
                    break;
                case "4":
                    mostrarStatus(roboSelecionado);
                    break;
                case "5":
                    ambiente.visualizarAmbiente();
                    break;
                case "6":
                    if (roboSelecionado != null)
                        executarAcoes(roboSelecionado, sc, ambiente, central);
                    else
                        System.out.println("Nenhum robô selecionado.");
                    break;
                case "7":
                    if (roboSelecionado != null) {
                        moverRobo(roboSelecionado, sc, ambiente);
                        ambiente.visualizarAmbiente();
                    } else {
                        System.out.println("Nenhum robô selecionado.");
                    }
                    break;
                case "8":
                    if (roboSelecionado != null) {
                        alternarEstado(roboSelecionado);
                        ambiente.visualizarAmbiente();
                    } else {
                        System.out.println("Nenhum robô selecionado.");
                    }
                    break;
                case "9":
                    central.exibirMensagens();
                    break;
                case "0":
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void listarPorTipo(ArrayList<Entidade> entidades) {
        System.out.println("\n== Robôs por tipo ==");
        for (Entidade e : entidades) {
            if (e instanceof Robo r) {
                System.out.println(e.getClass().getSimpleName() + " - ID: " + r.getId());
            }
        }
    }

    private static void listarPorEstado(ArrayList<Entidade> entidades) {
        System.out.println("\n== Robôs por estado ==");
        for (Entidade e : entidades) {
            if (e instanceof Robo r) {
                System.out.println(r.getId() + ": " + (r.estaLigado() ? "Ligado" : "Desligado"));
            }
        }
    }

    private static Entidade selecionarRobo(ArrayList<Entidade> entidades, Scanner sc) {
        System.out.println("\n== Selecione um robô ==");
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i) instanceof Robo r) {
                System.out.println(i + ": " + r.getId());
            }
        }
        System.out.print("Digite o número do robô: ");
        try {
            int index = Integer.parseInt(sc.nextLine());
            Entidade selecionado = entidades.get(index);
            if (selecionado instanceof Robo) return selecionado;
            else System.out.println("A entidade selecionada não é um robô.");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Entrada inválida. Tente novamente.");
        }
        return null;
    }

    private static void mostrarStatus(Entidade robo) {
        if (robo == null || !(robo instanceof Robo r)) {
            System.out.println("Nenhum robô selecionado.");
            return;
        }
        System.out.println("\n== Status do Robô ==");
        System.out.println("ID: " + r.getId());
        System.out.println("Posição: (" + r.getX() + ", " + r.getY() + ", " + r.getZ() + ")");
        System.out.println("Estado: " + (r.estaLigado() ? "Ligado" : "Desligado"));
    }

    private static void executarAcoes(Entidade robo, Scanner sc, Ambiente ambiente, CentralComunicacao central) {
        Robo r = (Robo) robo;

        if (!r.estaLigado()) {
            System.out.println("Robô está desligado!");
            return;
        }
        // Sensores
        if (r instanceof Sensoreavel s) {
            try {
                s.acionarSensores();
            } catch (RoboDesligadoException e) {
                System.out.println("Erro ao acionar sensores: " + e.getMessage());
            }
        }
        // Comunicação
        if (r instanceof Comunicavel c) {
            try {
                c.enviarMensagem(c, "Mensagem de teste do menu.");
            } catch (RoboDesligadoException e) {
                System.out.println("Erro ao enviar mensagem: " + e.getMessage());
            }
        }
        // Ação autônoma
        if (r instanceof Autonomo a) {
            try {
                a.decidirProximaAcao(ambiente);
            } catch (RoboDesligadoException e) {
                System.out.println("Erro ao decidir próxima ação: " + e.getMessage());
            }
        }
        // Alerta rápido
        if (r instanceof AlertaRapido ar) {
            try {
                ar.dispararAlerta("Alerta automático emitido!",
                        new ArrayList<>(ambiente.getEntidades()), central);
            } catch (RoboDesligadoException | ErroComunicacaoException e) {
                System.out.println("Erro ao disparar alerta: " + e.getMessage());
            }
        }
        // Manobra
        if (r instanceof Manobrador m) {
            System.out.print("Digite o contexto da manobra: ");
            String contexto = sc.nextLine();
            try {
                m.realizarManobra(contexto);
            } catch (RoboDesligadoException e) {
                System.out.println("Erro ao realizar manobra: " + e.getMessage());
            }
        }
    }

    private static void moverRobo(Entidade robo, Scanner sc, Ambiente ambiente) {
        Robo r = (Robo) robo;
        if (!r.estaLigado()) {
            System.out.println("Robô está desligado!");
            return;
        }

        try {
            System.out.print("Delta X: ");
            int dx = Integer.parseInt(sc.nextLine());
            System.out.print("Delta Y: ");
            int dy = Integer.parseInt(sc.nextLine());
            System.out.print("Delta Z: ");
            int dz = Integer.parseInt(sc.nextLine());

            ambiente.moverEntidade(r, r.getX() + dx, r.getY() + dy, r.getZ() + dz);
        } catch (Exception e) {
            System.out.println("Erro ao mover: " + e.getMessage());
        }
    }

    private static void alternarEstado(Entidade robo) {
        Robo r = (Robo) robo;
        if (r.estaLigado()) {
            r.desligar();
            System.out.println("Robô desligado.");
        } else {
            r.ligar();
            System.out.println("Robô ligado.");
        }
    }
}
