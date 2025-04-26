package LAB03.src;

import LAB03.src.Ambiente;
import LAB03.src.Robo;

public class SensorAltitude extends Sensor {
    public SensorAltitude(Robo robo, double raio) {
        super(robo, raio);
    }

    @Override
    public void monitorar(Ambiente ambiente) {
        System.out.println("Altitude atual do robo '" + robo.getNome() + "': " + robo.getAltitude());
    }
}
