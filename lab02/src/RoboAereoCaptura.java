import lab03.src.RoboAereo;

public class RoboAereoCaptura extends RoboAereo {
    private int resolucaoCamera; // resolução da câmera em megapixels

    public RoboAereoCaptura(String nome, String direcao, int posicaoX, int posicaoY, int altitudeMaxima, int resolucaoCamera) {
        super(nome, direcao, posicaoX, posicaoY, altitudeMaxima);
        this.resolucaoCamera = resolucaoCamera;
    }

    public void capturarImagem() {
        System.out.println(nome + " capturou uma imagem com resolução de " + resolucaoCamera + " megapixels.");
    }

    public int getResolucaoCamera() {
        return resolucaoCamera;
    }

    public void setResolucaoCamera(int resolucaoCamera) {
        this.resolucaoCamera = resolucaoCamera;
    }
}
