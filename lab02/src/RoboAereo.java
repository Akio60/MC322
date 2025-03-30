public class RoboAereo extends Robo {
    protected int altitude; // Altitude atual do robô
    protected int altitudeMaxima; // Altitude máxima permitida para o robô

    public RoboAereo(String nome, String direcao, int posicaoX, int posicaoY, int altitudeMaxima) {
        super(nome, direcao, posicaoX, posicaoY); // Inicializa o robô com os atributos herdados
        this.altitude = 0; // Inicializa a altitude como zero
        this.altitudeMaxima = altitudeMaxima; // Define a altitude máxima
    }

    public void subir(int metros) {
        // Verifica se o robô pode subir sem exceder a altitude máxima
        if (altitude + metros <= altitudeMaxima) {
            altitude += metros; // Aumenta a altitude
        } else {
            System.out.println("Altitude máxima excedida!"); // Exibe mensagem de erro
        }
    }

    public void descer(int metros) {
        // Verifica se o robô pode descer sem ficar abaixo do solo
        if (altitude - metros >= 0) {
            altitude -= metros; // Diminui a altitude
        } else {
            System.out.println("Não é possível descer abaixo do solo!"); // Exibe mensagem de erro
        }
    }

    public int getAltitude() {
        return altitude; // Retorna a altitude atual
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude; // Define a altitude atual
    }

    public int getAltitudeMaxima() {
        return altitudeMaxima; // Retorna a altitude máxima
    }

    public void setAltitudeMaxima(int altitudeMaxima) {
        this.altitudeMaxima = altitudeMaxima; // Define a altitude máxima
    }
}
