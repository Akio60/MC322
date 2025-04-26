package lab03.src;

public class SensorTerreno extends Sensor {
    public SensorTerreno(Robo robo, double raio) {
        super(robo, raio);
    }

    @Override
    public void monitorar(Ambiente ambiente) {
        System.out.println("Iniciando varredura de terreno...");
        boolean detectou = false;

        for (Obstaculo o : ambiente.getObstaculos()) {
            double dist = calcularDistancia(robo.getX(), robo.getY(), o.getX(), o.getY());
            if (dist <= raio) {
                detectou = true;
                System.out.printf("  > %s detectado a %.1f unidades\n    Posição: (%d,%d), Altura: %d\n",
                    o.getTipo().getDescricao(), dist, o.getX(), o.getY(), o.getAltura());
            }
        }

        if (!detectou) {
            System.out.println("Nenhum obstáculo detectado no raio de " + raio + " unidades");
        }
    }

    private double calcularDistancia(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
