import lab03.src.RoboAereo;

public class RoboAereoEntrega extends RoboAereo {
    private int capacidadeCarga; // capacidade máxima de carga em kg

    public RoboAereoEntrega(String nome, String direcao, int posicaoX, int posicaoY, int altitudeMaxima, int capacidadeCarga) {
        super(nome, direcao, posicaoX, posicaoY, altitudeMaxima);
        this.capacidadeCarga = capacidadeCarga;
    }

    public void entregarPacote() {
        System.out.println(nome + " está entregando um pacote com capacidade de " + capacidadeCarga + " kg.");
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(int capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }
}
