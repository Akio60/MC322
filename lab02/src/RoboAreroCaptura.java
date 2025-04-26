import lab03.src.RoboAereo;

public class RoboAreroCaptura extends RoboAereo {
    private int resolucaoCamera; // Resolução da câmera em megapixels

    public RoboAereoCaptura(String nome, String direcao, int posicaoX, int posicaoY, int altitudeMaxima, int resolucaoCamera) {
        super(nome, direcao, posicaoX, posicaoY, altitudeMaxima); // Inicializa o robô aéreo com os atributos herdados
        this.resolucaoCamera = resolucaoCamera; // Define a resolução da câmera
    }

    public void capturarImagem() {
        // Método específico para capturar uma imagem
        System.out.println(nome + " capturou uma imagem com resolução de " + resolucaoCamera + " megapixels.");
    }
}
