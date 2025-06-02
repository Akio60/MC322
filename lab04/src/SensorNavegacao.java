public class SensorNavegacao extends Sensor {
    public SensorNavegacao(Robo robo, double raio) {
        super(robo, raio);
    }

    @Override
    public void monitorar(Ambiente ambiente) {
        System.out.println("Status de Navegação:");
        System.out.printf("  > Posição atual: (%d,%d)\n", robo.getX(), robo.getY());
        System.out.printf("  > Altitude: %d\n", robo.getZ());

        // Verifica obstáculos no caminho
        for (Obstaculo o : ambiente.getObstaculos()) {
            double dist = calcularDistancia(robo.getX(), robo.getY(), o.getX(), o.getY());
            if (dist <= raio && o.getZ() >= robo.getZ()) {
                System.out.printf("  > Alerta: %s na direção (%d,%d), altura %d\n",
                    o.getTipo().getDescricao(), o.getX(), o.getY(), o.getZ());
            }
        }
    }

    private double calcularDistancia(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
