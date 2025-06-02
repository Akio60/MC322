/**
 * Classe abstrata base para todos os tipos de sensores.
 * Implementa o padrão Strategy para diferentes tipos de monitoramento.
 */
public abstract class Sensor {
    protected final double raio;  // Raio de alcance do sensor
    protected final Robo robo;    // Referência ao robô (composição)

    /**
     * Construtor padrão para sensores.
     * @param robo Robô ao qual o sensor será conectado
     * @param raio Alcance máximo do sensor
     */
    public Sensor(Robo robo, double raio) {
        this.robo = robo;
        this.raio = raio;
    }

    /**
     * Método abstrato de monitoramento.
     * Cada tipo de sensor implementa sua própria lógica.
     */
    public abstract void monitorar(Ambiente ambiente);

    /**
     * Retorna o raio de alcance do sensor.
     */
    public double getRaio() {
        return raio;
    }
}
