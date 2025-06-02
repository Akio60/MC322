public class SensorTatico extends Sensor {
    public SensorTatico(Robo robo, double raio) {
        super(robo, raio);
    }

    @Override
    public void monitorar(Ambiente ambiente) {
        System.out.println("Análise Tática:");
        boolean detectou = false;

        // Detecta bases
        for (Obstaculo o : ambiente.getObstaculos()) {
            if (o.getTipo() == TipoObstaculo.BASE_ALIADA || 
                o.getTipo() == TipoObstaculo.BASE_INIMIGA) {
                double dist = calcularDistancia(robo.getX(), robo.getY(), o.getX(), o.getY());
                if (dist <= raio) {
                    detectou = true;
                    System.out.printf("  > %s localizada a %.1f unidades (%d,%d)\n",
                        o.getTipo().getDescricao(), dist, o.getX(), o.getY());
                }
            }
        }

        // Detecta outros robôs
        for (Robo outroRobo : ambiente.getRobos()) {
            if (outroRobo != this.robo) {
                double dist = calcularDistancia(robo.getX(), robo.getY(), 
                                             outroRobo.getX(), outroRobo.getY());
                if (dist <= raio) {
                    detectou = true;
                    System.out.printf("  > Robô '%s' detectado a %.1f unidades (%d,%d)\n",
                        outroRobo.getId(), dist, outroRobo.getX(), outroRobo.getY());
                }
            }
        }

        if (!detectou) {
            System.out.println("Nenhum alvo detectado no raio de " + raio + " unidades");
        }
    }

    private double calcularDistancia(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
