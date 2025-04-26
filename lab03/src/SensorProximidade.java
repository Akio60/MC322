package lab03.src;

import lab03.src.Ambiente;
import lab03.src.Robo;

/**
 * Sensor que detecta obstáculos próximos ao robô
 * Herda da classe base Sensor
 */
public class SensorProximidade extends Sensor {
    public SensorProximidade(Robo robo, double raio) {
        super(robo, raio);
    }

    @Override
    public void monitorar(Ambiente ambiente) {
        boolean perigo = false;
        for (Obstaculo o : ambiente.getObstaculos()) {
            // Usar getters ao invés de acesso direto
            int dx = Math.abs(robo.getX() - (o.getPosicaoX1() + (o.getPosicaoX2() - o.getPosicaoX1())/2));
            int dy = Math.abs(robo.getY() - (o.getPosicaoY1() + (o.getPosicaoY2() - o.getPosicaoY1())/2));
            double dist = Math.sqrt(dx*dx + dy*dy);
            if (dist <= raio) {
                perigo = true;
                break;
            }
        }
        System.out.println("Sensor proximidade (raio=" + raio + ") detectou obstaculo perto: " + perigo);
    }
}