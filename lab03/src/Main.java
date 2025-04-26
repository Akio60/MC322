

import src.Ambiente;
import src.Robo;
import src.RoboAereo;
import src.RoboTerrestre;
import src.Obstaculo;
import src.SensorProximidade;
import src.SensorAltitude;
import src.TipoObstaculo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ambiente ambiente = new Ambiente(100, 100);
        // instanciar robos manualmente
        RoboTerrestre r1 = new RoboTerrestre("Rover", 10, 10);
        RoboAereo r2 = new RoboAereo("Drone", 20, 20, 5);
        // adicionar sensores
        r1.adicionarSensor(new SensorProximidade(r1, 5));
        r2.adicionarSensor(new SensorAltitude(r2, 0));

        ambiente.adicionarRobo(r1);
        ambiente.adicionarRobo(r2);
        ambiente.adicionarObstaculo(new Obstaculo(15, 15, 20, 20, TipoObstaculo.PAREDE));

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--- Menu ---");
            System.out.println("1. Mostrar status");
            System.out.println("2. Mover robo");
            System.out.println("3. Usar sensores");
            System.out.println("0. Sair");
            int opc = sc.nextInt();
            if (opc == 0) break;
            switch (opc) {
                case 1:
                    for (Robo r : ambiente.getRobos()) {
                        System.out.println(r.getNome() + " em (" + r.getX() + "," + r.getY() + ", alt=" + r.getAltitude() + ")");
                    }
                    break;
                case 2:
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
                case 3:
                    for (Robo r : ambiente.getRobos()) {
                        System.out.println("Sensores de " + r.getNome() + ":");
                        r.usarSensores(ambiente);
                    }
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }
        sc.close();
    }
}