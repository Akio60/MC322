public class RoboAereo extends Robo {
    protected int altitude;
    protected int altitudeMaxima;

    public RoboAereo(String nome,
                    String direcao, 
                    int posicaoX, 
                    int posicaoY, 
                    int altitudeMaxima) {
        super(nome, direcao, posicaoX, posicaoY);
        this.altitude = 0;
        this.altitudeMaxima = altitudeMaxima;
    }

    public void subir(int metros) {
        if (altitude + metros <= altitudeMaxima) {
            altitude += metros;
        } else {
            System.out.println("Altitude máxima excedida!");
        }
    }

    public void descer(int metros) {
        if (altitude - metros >= 0) {
            altitude -= metros;
        } else {
            System.out.println("Não é possível descer abaixo do solo!");
        }
    }
}
