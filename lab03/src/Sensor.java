package LAB03.src;

import LAB03.src.Ambiente;
import LAB03.src.Robo;

/**
 * Classe abstrata base para todos os tipos de sensores.
 * Implementa o padrão Composite com a classe Robo.
 */
public abstract class Sensor {
    protected final double raio;  // Raio de alcance do sensor
    protected final Robo robo;    // Robô ao qual o sensor está conectado

    public Sensor(Robo robo, double raio) {
        this.robo = robo;
        this.raio = raio;
    }

    // Método abstrato que cada tipo de sensor deve implementar
    public abstract void monitorar(Ambiente ambiente);
}
